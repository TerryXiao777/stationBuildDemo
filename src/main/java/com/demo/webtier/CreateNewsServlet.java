package com.demo.webtier;

import com.demo.bean.NetInforamtion;
import com.demo.bean.News;
import com.demo.bean.Temple;
import com.demo.dao.NetInformationDao;
import com.demo.dao.NewsDao;
import com.demo.dao.TempleDao;
import com.demo.dao.TypeDao;
import com.demo.tools.FileOperation;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class CreateNewsServlet extends HttpServlet {
    private String path = null;
    private Integer net_id = null;
    private NetInforamtion net = null;
    private Temple tem = null;
    private FileOperation fileOperation = null;

    private TempleDao templeDao = null;
    private NetInformationDao netInformationDao = null;
    private NewsDao newsDao = null;

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        fileOperation = new FileOperation();
        path = request.getRealPath("/");
        net_id = (Integer) request.getSession().getAttribute("maxId");
        netInformationDao = new NetInformationDao();
        net = netInformationDao.queryNetObject(net_id);
        templeDao = new TempleDao();
        tem = templeDao.queryTemObject(net.getNetType());
        this.createNews1(); // 第1步
        this.createNews2(request, response);// 第2步
        this.createNews3(request, response);// 第3步
        this.createNews4(request, response);// 第4步
        this.createNews5(request, response);// 第5步
        this.createNews6(request, response);// 第6步
        this.createNews7(request, response);// 第7步

    }

    /**
     * 第7步，将网站信息表中主页地址进行更改
     * @param request
     * @param response
     */
    private void createNews7(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("newsResult.jsp").forward(request,response);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 第6步，将保存在session中新闻类型与所对应的链接放置于news_top.htm
     * @param request
     * @param response
     */
    private void createNews6(HttpServletRequest request, HttpServletResponse response) {
        String templatetop = path +tem.getTemTop();
        fileOperation.createFile(templatetop);
        //byte bytes[] = fileOperation.readStream(templatetop);
        String template = fileOperation.readStream(templatetop);

        List<News> list = (List<News>) request.getSession().getAttribute("listTem");
        String topArrary = "";
        for (News news : list) {
            topArrary = topArrary + "<a href =" + news.getAddress()
                    + " target=_parent>" + news.getTypeName()
                    + "</a>&nbsp;&nbsp;";
        }
        template = template.replaceAll("###netName###", net.getNetIndividual());
        template = template.replaceAll("###type###", topArrary);
        String createNews = path + net.getNetAddress();
        fileOperation.writeStream(template, createNews, tem.getTemTop());
    }

    /**
     * 第5步，制作新闻类型页面
     * @param request
     * @param response
     */
    private void createNews5(HttpServletRequest request, HttpServletResponse response)  {
        List<News> list = null;
        List<News> listTem =new ArrayList<News>();
        String templateType = path + tem.getTemType();
        fileOperation.createFile(templateType);
        //byte bytes[] =
        String template = fileOperation.readStream(templateType);
        TypeDao typeDao = new TypeDao();
        NewsDao newsDao = new NewsDao();
        String[] types = typeDao.queryTypeNameArray(net_id);
        int j = 1;
        for (String type : types) {

            //template = new String(bytes);

            template = template.replaceAll("###type###", type);
            StringBuilder sql = new StringBuilder();
            sql.append("select * from t_news where net_id = ? and type_name = ?");
            String[] params ={String.valueOf(net_id),type};

            list = newsDao.queryNewsListCommon(sql.toString(),params);
            String typeArray = "";
            for (News news1 : list) {
                String title = "<tr><td width=372><a href="
                        + news1.getAddress() + " target=_blank>"
                        + news1.getConTitle() + "</a></td>";
                String createTime = "<td width=135>" + news1.getConTime()
                        + "</td><tr>";
                typeArray = typeArray + title + createTime;
            }
            template = template.replaceAll("###result###", typeArray);
            String createNews = path + net.getNetAddress();

            String type_tmp="type"+j+".html";
            fileOperation.writeStream(template, createNews, type_tmp);
            if (!type.equals("公告信息") && !type.equals("焦点导读")) {
                News newsTem = new News();
                newsTem.setTypeName(type);
                newsTem.setAddress(type_tmp);
                listTem.add(newsTem);
            }
            j++;
        }
        request.getSession().setAttribute("listTem", listTem);
    }

    /**
     * 第4步，制作首页面
     * @param request
     * @param response
     */
    private void createNews4(HttpServletRequest request, HttpServletResponse response) {
        String templateIndex = path + tem.getTemIndex();
        fileOperation.createFile(templateIndex);
        //byte bytes[] = fileOperation.readStream(templateIndex);
        String template = fileOperation.readStream(templateIndex);

        StringBuilder sql = new StringBuilder();
        sql.append("select * from t_news where type_name != '焦点导读' and type_name != '公告信息' and net_id = ?");
        String[] params = {String.valueOf(net_id)};

        NewsDao newsDao = new NewsDao();
        List<News> list = newsDao.queryNewsListCommon(sql.toString(),params);
        String indexArray = "";
        for (News news : list) {
            String title = "<tr height=30><td width=372><a href="
                    + news.getAddress() + " target=_blank>"
                    + news.getConTitle() + "</a></td>";
            String createTime = "<td width=135>" + news.getConTime()
                    + "</td></tr>";
            indexArray = indexArray + title + createTime;

        }
        template = template.replaceAll("###result###", indexArray);

        String createNews = path + net.getNetAddress();
        fileOperation.writeStream(template, createNews, "index.html");
    }

    /**
     * 第3步：生成左侧或右侧页面
     * @param request
     * @param response
     */
    private void createNews3(HttpServletRequest request, HttpServletResponse response) {
        String templateLeft = path + tem.getTemLeft();
        fileOperation.createFile(templateLeft);
        ///byte bytes[] = fileOperation.readStream(templateLeft);
        TypeDao typeDao = new TypeDao();
        String[] types = typeDao.queryTypeNameArray(net_id);
        String template =  fileOperation.readStream(templateLeft);

        String typeArray = "";
        for (String type : types) {
            typeArray = typeArray + "<option value=" + type + ">" + type
                    + "</option>";
        }
        template = template.replaceAll("###type###", typeArray);


        StringBuilder sql = new StringBuilder();
        sql.append("select * from t_news  where  type_name = '公告信息' and net_id = ?");
        String[] params = {String.valueOf(net_id)};
        NewsDao newsDao = new NewsDao();
        List<News> list = newsDao.queryNewsListCommon(sql.toString(),params);
        System.out.println(sql);
        String afficeArray = "";
        for (News news : list) {
            afficeArray = afficeArray + "<a href =" + news.getAddress()
                    + " target=_blank>" + news.getConTitle() + "</a><br><br>";
        }
        template = template.replaceAll("###afficheNews###", afficeArray);

        sql = new StringBuilder();
        sql.append("select * from t_news  where  type_name = '焦点导读' and net_id = ?");

        list = newsDao.queryNewsListCommon(sql.toString(),params);
        String focusArray = "";
        for (News news : list) {
            focusArray = focusArray + "<a href =" + news.getAddress()
                    + " target=_blank>" + news.getConTitle() + "</a><br><br>";
        }
        template = template.replaceAll("###focusNews###", focusArray);
        String createNews = path + net.getNetAddress();
        fileOperation.writeStream(template, createNews, tem.getTemLeft());
    }

    /**
     * 第2步，根据添加的新闻内容生成新闻查看内容
     * @param request
     * @param response
     */
    private void createNews2(HttpServletRequest request, HttpServletResponse response) {
        String templateContent = path + tem.getTemContent();
        fileOperation.createFile(templateContent);
        //byte bytes[] = fileOperation.readStream(templateContent);
        String template =fileOperation.readStream(templateContent);
        newsDao = new NewsDao();
        List<News> list = newsDao.queryNewsList(net_id);
        for (News news : list) {

            //template = new String(bytes);

            news.setAddress("content" + news.getId() + ".html");
            newsDao.updateNews(news);
            template = template.replaceAll("###title###", news.getConTitle());
            template = template.replaceAll("###content###", news
                    .getConContent());
            template = template.replaceAll("###createTime###", news
                    .getConTime());
            String createNewsContent = path + net.getNetAddress();
            fileOperation
                    .writeStream(template, createNewsContent, news.getId());
        }
    }

    /**
     * 第1步：将CSS样式与网站所需要的图片所在的文件夹进行拷贝指定文件夹下
     */
    private void createNews1() {
        String path1 = path + net.getNetAddress() + "/images";
        String address = path + tem.getTemImages();
        fileOperation.copyFile(address, path1);
    }
}
