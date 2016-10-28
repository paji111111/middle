package com.jason.myself.register;

import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liuzhixin on 2016/10/26.
 */
public class DBUtil {

    private static final String url = "jdbc:mysql://127.0.0.1:3306/test";
    private static final String name = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "root";

    private static Lock lock = new ReentrantLock();

    private static Queue<Connection> connectionQueue = new ConcurrentLinkedDeque<Connection>();

    private static void reAddConnection(){
        try {
            if (!connectionQueue.isEmpty())
                return;
            Class.forName(name);//指定连接类型
            for (int i = 0; i < 10; i++) {
                connectionQueue.add(DriverManager.getConnection(url, user, password));//获取连接
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() {
        for (;;) {
            Connection connection = connectionQueue.poll();
            if (connection == null) {
                try {
                    if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                        reAddConnection();
                    }else{
                        continue;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            } else {
                return connection;
            }
        }
    }

    public static ResultSet executeQuery(String querySql) throws SQLException {
        PreparedStatement pst = getConnection().prepareStatement(querySql);//准备执行语句
        return pst.executeQuery();
    }

    public static int executeUpdateOrInsert(String updateSql) throws SQLException {
        PreparedStatement pst = getConnection().prepareStatement(updateSql);//准备执行语句
        return pst.executeUpdate();
    }
}
