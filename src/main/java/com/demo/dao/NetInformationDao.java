package com.demo.dao;

import com.demo.bean.NetInforamtion;
import com.demo.tools.JDBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NetInformationDao {
    private JDBConnection connection = null;

    public NetInformationDao(){
        connection = new JDBConnection();
    }

    public NetInforamtion queryNetObject(Integer id) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from t_net_inforamtion where id = ?");
        String[] params={String.valueOf(id)};
        connection = new JDBConnection();
        NetInforamtion net = new NetInforamtion();
        ResultSet rs = connection.queryByPsStatement(sql.toString(),params);
        try {
            while (rs.next()) {
                net = new NetInforamtion();
                net.setId(rs.getInt(1));
                net.setNetIndividual(rs.getString(2));
                net.setNetType(rs.getString(3));
                net.setNetAddress(rs.getString(4));
                net.setAccount(rs.getString(5));
                net.setSign(rs.getInt(6));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return net;
    }

    /**
     *
     * @param net_sql
     * @param account
     * @return
     */
    public List<NetInforamtion> queryNetList(String net_sql,String account) {
        List<NetInforamtion> list = new ArrayList<NetInforamtion>();
        String[] params= null;
        connection = new JDBConnection();
        ResultSet rs = null;
        if(!"".equals(account)){
            params= new String[]{account};
        }
        rs = connection.queryByPsStatement(net_sql,params);
        NetInforamtion net;
        try {
            while (rs.next()) {
                net = new NetInforamtion();
                net.setId(rs.getInt(1));
                net.setNetIndividual(rs.getString(2));
                net.setNetType(rs.getString(3));
                net.setNetAddress(rs.getString(4));
                net.setAccount(rs.getString(5));
                net.setSign(rs.getInt(6));
                list.add(net);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public Boolean queryNetObject(String account, String net_individual) {
        Boolean flag = false;
        StringBuilder sql = new StringBuilder();
        sql.append("select * from t_net_inforamtion where account = ? ");
        sql.append("and net_individual = ?");
        String[] params={account,net_individual};
        connection = new JDBConnection();

        ResultSet rs = connection.queryByPsStatement(sql.toString(),params);
        try {
            while (rs.next()) {
                flag = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public Boolean deleteNetInformation(Integer net_id) {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from t_net_inforamtion where id = ?");
        String[] params={String.valueOf(net_id)};
        connection = new JDBConnection();

        Boolean flag = connection.updateData(sql.toString(),params);
        connection.closeAll();
        return flag;
    }

    public Boolean addNetInformation(NetInforamtion net) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into t_net_inforamtion (net_individual,net_type,net_address,account,sign) ");
        sql.append("values (?,?,?,?,?)");
        String[] params={net.getNetIndividual(),net.getNetType(),net.getNetAddress(),net.getAccount(),
        String.valueOf(net.getSign())};

        connection = new JDBConnection();
        Boolean flag = connection.updateData(sql.toString(),params);
        connection.closeAll();

        return flag;
    }

    public Boolean updateNetInformation(NetInforamtion net) {
        StringBuilder sql = new StringBuilder();
        sql.append("update t_net_inforamtion set net_individual =?,net_type = ? ");
        sql.append("where id = ?");
        String[] params={net.getNetIndividual(),net.getNetType(),
                String.valueOf(net.getId())};

        connection = new JDBConnection();
        Boolean flag = connection.updateData(sql.toString(),params);
        connection.closeAll();
        return flag;
    }

    public Integer queryMaxId() {
        StringBuilder sql = new StringBuilder();
        sql.append("select max(id) as maxId from t_net_inforamtion");
        String[] params={};
        ResultSet rs = connection.queryByPsStatement(sql.toString(),params);
        Integer maxId = -1;
        try {
            while (rs.next()) {
                maxId = rs.getInt("maxId");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return maxId;
    }
}
