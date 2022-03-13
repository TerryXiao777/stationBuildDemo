package com.demo.webtier;

import com.demo.bean.NetInforamtion;
import com.demo.bean.News;
import com.demo.bean.Type;
import com.demo.bean.UserInfo;
import com.demo.dao.NetInformationDao;
import com.demo.dao.NewsDao;
import com.demo.dao.TypeDao;
import com.demo.tools.JDBConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class NetInformationServlet extends HttpServlet {
    private String method = "";

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        method = request.getParameter("method");

        // 添加网站信息
        if (method.equals("addNetInformation")) {
            this.addNetInformation(request, response);
        }

        // 添加网站新闻类别
        if (method.equals("addType")) {
            this.addType(request, response);
        }

        // 添加内容
        if (method.equals("addNews")) {
            this.addNews(request, response);
        }

        // 重新建立网站
        if (method.equals("cannleNews")) {
            this.cannleNews(request, response);
        }

        // 删除新闻
        if (method.equals("deleteNews")) {
            this.deleteNews(request, response);
        }

        // 查询新闻类别
        if (method.equals("queryType")) {
            this.queryType(request, response);
        }

        // 删除新闻类别
        if (method.equals("deleteType")) {
            this.deleteType(request, response);
        }

        // 修改新闻类别
        if (method.equals("updateType")) {
            this.updateType(request, response);
        }

        // 查询新闻内容
        if (method.equals("queryNews")) {
            this.queryNews(request, response);
        }

        // 修改新闻内容
        if (method.equals("updateNews")) {
            this.updateNews(request, response);
        }

        // 以ID为编号，实现查询操作
        if (method.equals("queryNewsObject")) {
            this.queryNewsObject(request, response);
        }

        // 修改网站名称
        if (method.equals("updateNetInformation")) {
            this.updateNetInformation(request, response);
        }

        // 用户登录后进入网站
        if (method.equals("enterNetInformation")) {
            this.enterNetInformation(request, response);
        }

    }
    private void enterNetInformation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer net_id = Integer.valueOf(request.getParameter("net_id"));
        Integer sign = Integer.valueOf(request.getParameter("sign"));
        request.getSession().setAttribute("maxId", net_id);
        request.getSession().setAttribute("sign", sign);
        NewsDao newsDao = new NewsDao();
        List list = newsDao.queryNewsList(net_id);
        request.setAttribute("newsList", list);
        request.getRequestDispatcher("net_queryNews.jsp").forward(request,
                response);

    }

    private void updateNetInformation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer net_id = (Integer) request.getSession().getAttribute("maxId");
        String net_individual = request.getParameter("net_individual");
        String net_type = request.getParameter("net_type");
        NetInforamtion net = new NetInforamtion();
        net.setId(net_id);
        net.setNetIndividual(net_individual);
        net.setNetType(net_type);
        NetInformationDao netInformationDao = new NetInformationDao();
        netInformationDao.updateNetInformation(net);
        String path = request.getParameter("path");
        request.getRequestDispatcher(path).forward(request, response);
    }

    // 以ID为编号，实现查询操作
    private void queryNewsObject(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        NewsDao newsDao = new NewsDao();
        List list = newsDao.queryNewsList2(id);
        if (list.size() == 1) {
            News news = (News) list.get(0);
            request.setAttribute("news", news);
            request.getRequestDispatcher("net_updateNews.jsp")
                    .forward(request, response);
        }
    }

    private void updateNews(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer net_id = (Integer) request.getSession().getAttribute("maxId");
        NewsDao newsDao = new NewsDao();
        News news = new News();
        news.setConTitle(request.getParameter("con_title"));
        news.setConContent(request.getParameter("con_content"));
        news.setTypeName(request.getParameter("type_name"));
        news.setConTime(request.getParameter("con_time"));
        news.setId(Integer.valueOf(request.getParameter("id")));
        Integer type_id = Integer.valueOf(request.getParameter("type_id"));
        news.setTypeId(type_id);
        TypeDao typeDao = new TypeDao();
        news.setTypeName(typeDao.queryTypeName(type_id));
        newsDao.updateNews2(news);
        List list = newsDao.queryNewsList(net_id);
        request.setAttribute("newsList", list);
        request.getRequestDispatcher("net_queryNews.jsp").forward(request, response);
    }

    private void queryNews(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer net_id = (Integer) request.getSession().getAttribute("maxId");
        NewsDao newsDao = new NewsDao();
        List list = newsDao.queryNewsList(net_id);
        if (null != request.getParameter("type_id")) {
            Integer type_id = Integer.valueOf(request.getParameter("type_id"));

            list = newsDao.queryNewsList3(net_id,type_id);
        }
        request.setAttribute("newsList", list);
        request.getRequestDispatcher("net_queryNews.jsp").forward(request, response);
    }

    // 修改新闻类别
    private void updateType(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer net_id = (Integer) request.getSession().getAttribute("maxId");
        Integer type_id = Integer.valueOf(request.getParameter("ids"));
        String type_name = request.getParameter("type_name");

        NewsDao newsDao = new NewsDao();
        newsDao.updateNews3(net_id,type_id,type_name);

        this.queryType(request, response);
    }

    // 删除新闻类别
    private void deleteType(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer net_id = (Integer) request.getSession().getAttribute("maxId");
        Integer type_id = Integer.valueOf(request.getParameter("type_id"));
        TypeDao typeDao = new TypeDao();
        typeDao.deleteType(type_id, net_id);
        this.queryType(request, response);
    }

    // 查询新闻类别
    private void queryType(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer net_id = (Integer) request.getSession().getAttribute("maxId");
        TypeDao typeDao = new TypeDao();
        request.setAttribute("typlist", typeDao.queryTypeList(net_id));
        request.getRequestDispatcher("net_queryType.jsp").forward(request, response);
    }

    // 删除新闻
    private void deleteNews(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        NewsDao newsDao = new NewsDao();
        newsDao.deleteNews(id);
        Integer net_id = (Integer) request.getSession().getAttribute("maxId");
        request.setAttribute("newsList", newsDao.queryNewsList(net_id));
        request.getRequestDispatcher("net_queryNews.jsp").forward(request, response);
    }

    // 重新建议网站
    private void cannleNews(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (null != request.getSession().getAttribute("maxId")) {
            Integer net_id = (Integer) request.getSession().getAttribute(
                    "maxId");
            NewsDao newsDao = new NewsDao();
            NetInformationDao netInformationDao = new NetInformationDao();
            TypeDao typeDao = new TypeDao();
            NetInforamtion net = netInformationDao.queryNetObject(net_id);
            String address = request.getRealPath("/") + net.getNetAddress();
            File file = new File(address);
            if (file.mkdir()) {
                file.delete();
            }
            newsDao.deleteNewsList(net_id);
            typeDao.deleteTypeList(net_id);
            netInformationDao.deleteNetInformation(net_id);
            request.getSession().removeAttribute("net_id");
        }
        request.getRequestDispatcher("net_addInformation.jsp").forward(request, response);

    }

    // 添加内容
    private void addNews(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        News news = new News();
        news.setConContent(request.getParameter("con_content"));
        news.setConTime(request.getParameter("con_time"));
        news.setConTitle(request.getParameter("con_title"));
        Integer net_id = (Integer) request.getSession().getAttribute("maxId");
        news.setNetId(net_id);
        news.setSign((Integer) request.getSession().getAttribute("sign"));
        Integer type_id = Integer.valueOf(request.getParameter("type_id"));
        news.setTypeId(type_id);
        TypeDao typeDao = new TypeDao();
        news.setTypeName(typeDao.queryTypeName(type_id));
        NewsDao newsDao = new NewsDao();

        if (newsDao.addNews(news)) {
            request.setAttribute("newsList", newsDao.queryNewsList(net_id));
            request.getRequestDispatcher("net_queryNews.jsp").forward(request, response);

        }
        else {
            request.setAttribute("result", "新闻添加失败！");
            request.getRequestDispatcher("net_addNews.jsp").forward(request, response);
        }

    }

    // 添加网站信息
    private void addNetInformation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NetInformationDao dao = new NetInformationDao();
        NetInforamtion net = new NetInforamtion();
        String net_individual = request.getParameter("net_individual");
        String net_type = request.getParameter("net_type");
        Integer sign = new Integer((int) System.currentTimeMillis());
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
                "userInfo");
        String address = userInfo.getAddress() + sign;
        net.setNetIndividual(net_individual);
        net.setNetType(net_type);
        net.setAccount(userInfo.getAccount());
        net.setSign(sign);
        net.setNetAddress(address);
        if (!dao.queryNetObject(userInfo.getAccount(), net_individual)) {

            if (dao.addNetInformation(net)) {
                request.getSession().setAttribute("sign", sign);
                request.getSession().setAttribute("maxId", dao.queryMaxId());
                request.getRequestDispatcher("net_addType.jsp").forward(request, response);
            }
            else {
                request.setAttribute("result", "添加网站信息失败！！！");
                request.getRequestDispatcher("net_addInformation.jsp")
                        .forward(request, response);
            }
        }
        else {
            request.setAttribute("result", "您输入的网站信息重复！！！");
            request.getRequestDispatcher("net_addInformation.jsp").forward(
                    request, response);
        }

    }

    // 添加网站新闻类别
    private void addType(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer sign = (Integer) request.getSession().getAttribute("sign");
        Integer maxId = (Integer) request.getSession().getAttribute("maxId");
        String net_type = request.getParameter("type_name");
        Type type = new Type();
        type.setNetId(maxId);
        type.setSign(sign);
        TypeDao dao = new TypeDao();
        String result = "添加类别失败！";
        if (!dao.queryType(maxId, net_type)) {
            String type_name[] = {"焦点导读", "公告信息"};
            if (dao.queryTypeNameArray(maxId).length == 0) {
                for (String type_name1 : type_name) {
                    if (!dao.queryType(maxId, type_name1)) {
                        type.setTypeName(type_name1);
                        dao.addType(type);
                    }
                }
            }
            type.setTypeName(net_type);
            if (dao.addType(type)) {
                result = "添加类别成功！";
            }
        }
        else {
            result = "您添加的新闻类别重复！！";
        }
        request.setAttribute("result", result);
        request.getRequestDispatcher("net_addType.jsp").forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
