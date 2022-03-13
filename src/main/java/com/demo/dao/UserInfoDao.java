package com.demo.dao;

import com.demo.bean.UserInfo;
import com.demo.tools.JDBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfoDao {
    private JDBConnection connection = null;

    public UserInfoDao() {
        connection = new JDBConnection();
    }

    public Boolean user_save(UserInfo userInfo) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into t_user_info (account,password,realname,sex,age,profession,address) ");
        sql.append("values(?,?,?,?,?,?,?)");
        String[] params={userInfo.getAccount(),userInfo.getPassword(),userInfo.getRealname()
                ,userInfo.getSex(),String.valueOf(userInfo.getAge()),userInfo.getProfession()
                ,userInfo.getAddress()};
        connection = new JDBConnection();
        return connection.updateData(sql.toString(), params);
    }

    public UserInfo user_queryObject(String account) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from t_user_info where account = ?");
        String[] params={account};
        ResultSet rs = connection.queryByPsStatement(sql.toString(),params);
        UserInfo userInfo = new UserInfo();
        try {
            if (rs.next()) {
                userInfo.setId(rs.getInt(1));
                userInfo.setAccount(rs.getString(2));
                userInfo.setPassword(rs.getString(3));
                userInfo.setRealname(rs.getString(4));
                userInfo.setSex(rs.getString(5));
                userInfo.setAge(rs.getInt(6));
                userInfo.setProfession(rs.getString(7));
                userInfo.setAddress(rs.getString(8));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connection.closeAll();
        }
        return userInfo;
    }

    public Boolean user_updateAddress(String account, String address) {
        StringBuilder sql = new StringBuilder();
        sql.append("update t_user_info set address = ? where account = ?");
        String[] params={address,account};
        connection = new JDBConnection();
        Boolean flag = connection.updateData(sql.toString(), params);
        connection.closeAll();
        return flag;
    }

}
