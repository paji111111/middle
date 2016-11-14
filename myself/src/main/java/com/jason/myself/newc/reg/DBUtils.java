package com.jason.myself.newc.reg;

import com.jason.myself.register.NodeData;
import com.jason.myself.register.NodeType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by liuzhixin on 2016/10/27.
 */
public class DBUtils {

    private static final String url = "jdbc:mysql://127.0.0.1:3306/study";
    private static final String name = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "root";

    private static Queue<Connection> connectionQueue = new ConcurrentLinkedDeque<Connection>();

    private static void reAddConnection() throws ClassNotFoundException, SQLException {
            if (!connectionQueue.isEmpty())
                return;
            Class.forName(name);//指定连接类型
            for (int i = 0; i < 10; i++) {
                connectionQueue.add(DriverManager.getConnection(url, user, password));//获取连接
            }
    }
    private static synchronized Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection connection = connectionQueue.poll();
        if (connection == null) {
            reAddConnection();
            connection = connectionQueue.poll();
        }

        return connection;
    }

    private static void close(Connection connection){
        connectionQueue.offer(connection);
    }

    public static boolean isNodeSigned(String nodePath, NodeType nodeType , String host, String port) throws SQLException, ClassNotFoundException {
        String sql =  "select * from nodedata where nodename='"+nodePath +" and `type`="+ nodeType.getCode() +"' and ip = '"+host+"' and port = '"+port+"' limit 1";
        Connection connection = getConnection();
        ResultSet rs = connection.prepareStatement(sql).executeQuery();

        if (!rs.next()){
            sql = " insert into nodedata(nodename,`type`,ip,port,status) values ( '"+ nodePath+"','"+nodeType.getCode()+"','"+host+"','"+port+"','"+1+"')";
            connection.prepareStatement(sql).executeUpdate();
        }
        close(connection);
        return true;
    }


    public static List<NodeData> queryNodeDataList(NodeData nodeData) throws SQLException, ClassNotFoundException {
        String sql = "select * from nodedata ";
        if (nodeData!=null){
            sql += " where `type` = "+ nodeData.getType();
        }
        Connection connection = getConnection();
        ResultSet rs = connection.prepareStatement(sql).executeQuery();
        List<NodeData> list = new ArrayList<>();
        while (rs.next()){
            NodeData nodeDataTmp = new NodeData();
            nodeDataTmp.setNodeName(rs.getString("nodename"));
            nodeDataTmp.setType(rs.getInt("type"));
            nodeDataTmp.setIp(rs.getString("ip"));
            nodeDataTmp.setPort(rs.getString("port"));

            list.add(nodeDataTmp);
        }
        return list;
    }



}
