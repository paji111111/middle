package com.jason.myself.newc.reg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public static boolean isNodeExist(String nodepath) throws SQLException, ClassNotFoundException {

        String sql =  "select * from nodestore where path='"+nodepath +"' limit 1";
        Connection connection = getConnection();
        ResultSet rs = connection.prepareStatement(sql).executeQuery();

        if (!rs.next()){
            sql = " insert into nodestore(path) VALUES ( '"+ nodepath+"')";
            connection.prepareStatement(sql).executeUpdate();
        }
        close(connection);
        return true;
    }

    public static boolean isNodeSigned(String nodePath, String host, String port) throws SQLException, ClassNotFoundException {
        String sql =  "select * from nodedate where nodename='"+nodePath +"' and ip = '"+host+"' and port = '"+port+"' limit 1";
        Connection connection = getConnection();
        ResultSet rs = connection.prepareStatement(sql).executeQuery();

        if (!rs.next()){
            sql = " insert into nodedate(nodename,ip,port,status) values ( '"+ nodePath+"','"+host+"','"+port+"','"+1+"')";
            connection.prepareStatement(sql).executeUpdate();
        }
        close(connection);
        return true;
    }




}
