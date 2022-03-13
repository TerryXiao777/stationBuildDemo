package com.demo.dao;

import com.demo.bean.Type;
import com.demo.tools.JDBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeDao {
    private JDBConnection connection = null;
    public TypeDao(){
        connection = new JDBConnection();
    }

    public Boolean deleteType(Integer type_id,Integer net_id){
        StringBuilder sql = new StringBuilder();
        sql.append("delete from t_news where type_id = ? and net_id = ?");
        String[] params={String.valueOf(type_id),String.valueOf(net_id)};
        connection = new JDBConnection();
        connection.updateData(sql.toString(), params);
        StringBuilder sql2 = new StringBuilder();
        sql2.append("delete from t_type where id = ? and net_id = ?");
        connection.updateData(sql2.toString(), params);
        return true;
    }


    public Boolean deleteTypeList(Integer net_id) {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from t_type where net_id = ?");
        String[] params={String.valueOf(net_id)};
        connection = new JDBConnection();
        Boolean flag = connection.updateData(sql.toString(), params);
        connection.closeAll();
        return flag;
    }

    public Boolean addType(Type type) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into t_type(type_name,net_id,sign) values(?,?,?)");
        String[] params={type.getTypeName(),String.valueOf(type.getNetId()),String.valueOf(type.getSign())};
        connection = new JDBConnection();
        Boolean flag = connection.updateData(sql.toString(),params);
        connection.closeAll();
        return flag;
    }

    public Boolean queryType(Integer net_id,String type_name){
        Boolean falg=false;
        StringBuilder sql = new StringBuilder();
        sql.append("select * from t_type where net_id = ? and type_name = ?");
        String[] params={String.valueOf(net_id),type_name};
        connection = new JDBConnection();
        ResultSet rs = connection.queryByPsStatement(sql.toString(),params);
        try {
            while(rs.next()){
                falg=true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return falg;
    }

    public List<Type> queryTypeList(Integer net_id) {
        List<Type> list = new ArrayList<Type>();
        StringBuilder sql = new StringBuilder();
        sql.append("select * from t_type where net_id = ?");
        String[] params={String.valueOf(net_id)};
        connection = new JDBConnection();

        ResultSet rs = connection.queryByPsStatement(sql.toString(),params);
        try {
            while (rs.next()) {
                Type type = new Type();
                type.setId(rs.getInt(1));
                type.setTypeName(rs.getString(2));
                type.setNetId(rs.getInt(3));
                type.setSign(rs.getInt(4));
                list.add(type);
            }
        }
        catch (SQLException e) {
            list = null;
            e.printStackTrace();
        }
        return list;
    }

    public String queryTypeName(Integer type_id) {
        String type_name = "";
        StringBuilder sql = new StringBuilder();
        sql.append("select * from t_type where id = ?");
        String[] params={String.valueOf(type_id)};
        connection = new JDBConnection();

        ResultSet rs = connection.queryByPsStatement(sql.toString(),params);
        try {
            while (rs.next()) {
                type_name = rs.getString("type_name");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return type_name;
    }

    // 分组查询新闻类别
    public String[] queryTypeNameArray(Integer net_id) {
        String[] type = null;
        connection = new JDBConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("select type_name from t_type where type_name != '焦点导读' and  type_name != '公告信息' ");
        sql.append("and net_id = ?");
        String[] params={String.valueOf(net_id)};

        ResultSet rs = connection.queryByPsStatement(sql.toString(),params);
        try {
            rs.last();
            type = new String[rs.getRow()];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                type[i] = rs.getString("type_name");
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connection.closeAll();
        }
        return type;
    }
}
