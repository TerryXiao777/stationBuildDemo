package com.demo.dao;

import com.demo.bean.News;
import com.demo.tools.JDBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsDao {
    private JDBConnection connection = null;

    public NewsDao(){
        connection = new JDBConnection();
    }

    public Boolean deleteNews(Integer id) {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from t_news where id = ?");
        String[] params={String.valueOf(id)};
        connection = new JDBConnection();
        connection.updateData(sql.toString(), params);
        Boolean flag = connection.updateData(sql.toString(),params);
        connection.closeAll();
        return flag;
    }

    public Boolean deleteNewsList(Integer net_id) {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from t_news where net_id = ?");
        String[] params={String.valueOf(net_id)};
        connection = new JDBConnection();
        connection.updateData(sql.toString(), params);
        Boolean flag = connection.updateData(sql.toString(),params);
        connection.closeAll();
        return flag;
    }

    public Boolean addNews(News news) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into t_news (type_id,type_name,con_title,con_content,con_time,sign,net_id) ");
        sql.append("values (?,?,?,?,?,?,?)");
        String[] params={String.valueOf(news.getTypeId()),news.getTypeName(),news.getConTitle(),
        news.getConContent(),news.getConTime(),String.valueOf(news.getSign()),
                String.valueOf(news.getNetId())};
        connection = new JDBConnection();
        connection.updateData(sql.toString(), params);
        Boolean flag = connection.updateData(sql.toString(),params);

        connection.closeAll();
        return flag;
    }

    public Boolean updateNews(News news) {
        StringBuilder sql = new StringBuilder();
        sql.append("update t_news set address = ? where id = ?");
        String[] params={news.getAddress(),String.valueOf(news.getId())};
        connection = new JDBConnection();
        connection.updateData(sql.toString(), params);
        Boolean flag = connection.updateData(sql.toString(),params);

        connection.closeAll();
        return flag;
    }

    public Boolean updateNews2(News news) {
        StringBuilder sql = new StringBuilder();
        sql.append("update t_news set type_id = ? ,type_name=?,con_title = ?,");
        sql.append("con_content = ? , con_time = ? where id = ?");
        String[] params={String.valueOf(news.getTypeId()),news.getTypeName(),news.getConTitle(),
                news.getConContent(),news.getConTime(),
                String.valueOf(news.getId())};
        connection = new JDBConnection();
        connection.updateData(sql.toString(), params);
        Boolean flag = connection.updateData(sql.toString(),params);
        connection.closeAll();
        return flag;
    }

    public Boolean updateNews3(Integer net_id,Integer type_id,String type_name){
        StringBuilder sql = new StringBuilder();
        sql.append("update t_news set type_id = ? ,type_name =? where net_id = ?");
        String[] params={String.valueOf(type_id),type_name,String.valueOf(net_id)};
        connection = new JDBConnection();
        Boolean flag = connection.updateData(sql.toString(), params);
        StringBuilder sql2 = new StringBuilder();
        sql2.append("update t_type set type_name = ? where id = ? and net_id = ?");
        String[] params2 = {type_name,String.valueOf(type_id),String.valueOf(net_id)};
        flag = connection.updateData(sql2.toString(), params2);
        connection.closeAll();
        return flag;
    }

    public List<News> queryNewsList(Integer net_id) {
        List<News> list = new ArrayList<News>();
        StringBuilder sql = new StringBuilder();
        sql.append("select * from t_news where net_id = ? order by id desc");
        String[] params={String.valueOf(net_id)};
        connection = new JDBConnection();
        ResultSet rs = connection.queryByPsStatement(sql.toString(),params);
        try {
            while (rs.next()) {
                News news = new News();
                news.setId(rs.getInt(1));
                news.setTypeId(rs.getInt(2));
                news.setTypeName(rs.getString(3));
                news.setConTitle(rs.getString(4));
                news.setConContent(rs.getString(5));
                news.setConTime(rs.getString(6));
                news.setSign(rs.getInt(7));
                news.setNetId(rs.getInt(8));
                news.setAddress(rs.getString(9));
                list.add(news);
            }
        }
        catch (SQLException e) {
            list = null;
        }
        return list;
    }

    public List<News> queryNewsList2(Integer id) {
        News news = null;
        List<News> list = new ArrayList<News>();
        StringBuilder sql = new StringBuilder();
        sql.append("select * from t_news where id =?");
        String[] params={String.valueOf(id)};
        connection = new JDBConnection();
        ResultSet rs = connection.queryByPsStatement(sql.toString(),params);
        try {
            while (rs.next()) {
                news = new News();
                news.setId(rs.getInt(1));
                news.setTypeId(rs.getInt(2));
                news.setTypeName(rs.getString(3));
                news.setConTitle(rs.getString(4));
                news.setConContent(rs.getString(5));
                news.setConTime(rs.getString(6));
                news.setSign(rs.getInt(7));
                news.setNetId(rs.getInt(8));
                news.setAddress(rs.getString(9));
                list.add(news);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            list = null;
        }
        return list;
    }

    public List<News> queryNewsList3(Integer net_id,Integer type_id) {
        News news = null;
        List<News> list = new ArrayList<News>();
        StringBuilder sql = new StringBuilder();
        sql.append("select * from t_news where net_id  =? and type_id = ?");
        String[] params={String.valueOf(net_id),String.valueOf(type_id)};
        connection = new JDBConnection();
        ResultSet rs = connection.queryByPsStatement(sql.toString(),params);
        try {
            while (rs.next()) {
                news = new News();
                news.setId(rs.getInt(1));
                news.setTypeId(rs.getInt(2));
                news.setTypeName(rs.getString(3));
                news.setConTitle(rs.getString(4));
                news.setConContent(rs.getString(5));
                news.setConTime(rs.getString(6));
                news.setSign(rs.getInt(7));
                news.setNetId(rs.getInt(8));
                news.setAddress(rs.getString(9));
                list.add(news);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            list = null;
        }
        return list;
    }

    public List<News> queryNewsListCommon(String sql,String[] params){
        News news = null;
        List<News> list = new ArrayList<News>();
        connection = new JDBConnection();
        ResultSet rs = connection.queryByPsStatement(sql,params);
        try {
            while (rs.next()) {
                news = new News();
                news.setId(rs.getInt(1));
                news.setTypeId(rs.getInt(2));
                news.setTypeName(rs.getString(3));
                news.setConTitle(rs.getString(4));
                news.setConContent(rs.getString(5));
                news.setConTime(rs.getString(6));
                news.setSign(rs.getInt(7));
                news.setNetId(rs.getInt(8));
                news.setAddress(rs.getString(9));
                list.add(news);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            list = null;
        }
        return list;
    }
}
