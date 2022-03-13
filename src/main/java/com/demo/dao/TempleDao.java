package com.demo.dao;

import com.demo.bean.Temple;
import com.demo.tools.JDBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TempleDao {
    private JDBConnection connection = null;

    public TempleDao(){
        connection = new JDBConnection();
    }

    public Temple queryTemObject(String tem_name) {
        Temple tem = new Temple();
        StringBuilder sql = new StringBuilder();
        sql.append("select * from t_temple where tem_name = ?");
        String[] params={tem_name};
        connection = new JDBConnection();
        ResultSet rs = connection.queryByPsStatement(sql.toString(),params);
        try {
            while (rs.next()) {
                tem.setTemName(rs.getString(1));
                tem.setTemIndex(rs.getString(2));
                tem.setTemTop(rs.getString(3));
                tem.setTemLeft(rs.getString(4));
                tem.setTemType(rs.getString(5));
                tem.setTemContent(rs.getString(6));
                tem.setTemImages(rs.getString(7));
                tem.setTem(rs.getString(8));
            }
        }
        catch (SQLException e) {

            e.printStackTrace();
        }
        return tem;
    }
}
