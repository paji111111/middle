package com.jason.myself.tool;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.collections4.CollectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzhixin on 2016/11/24.
 */
public class DBAsycTool {

    public static String jdbcUrlDev = "jdbc:mysql://192.168.50.249:3306/carstock_dev?useUnicode=true&characterEncoding=UTF-8&user=user_crm&password=root";

    public static String jdbcUrlTest = "jdbc:mysql://192.168.50.249:3306/carstock_test?useUnicode=true&characterEncoding=UTF-8&user=user_crm&password=root";

    public static Statement devStmt , testStatement;

    public static Map<String,List<String>> tableMap = new HashedMap();
    public static Map<String,List<String>> alterTableMap = new HashedMap();
    public static List<String> createTableList = new ArrayList<>();

    static {
        try {
            Connection devConnection , testConnection;
            Class.forName("com.mysql.jdbc.Driver");
            devConnection = DriverManager.getConnection(jdbcUrlDev);
            devStmt  = devConnection.createStatement();


            testConnection = DriverManager.getConnection(jdbcUrlTest);
            testStatement =testConnection.createStatement();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws SQLException {
        // 查询新表旧表
        tableListMap();

        // 查询表更改
        tableAlter();

    }

    private static void tableAlter() {
        List<String> devTableList = tableMap.get("devTable");

        if (CollectionUtils.isNotEmpty(devTableList)) {
            for (String devTable :devTableList){

            }
        }
    }

    private static void tableListMap() throws SQLException {
        String sql = "show tables";

        List<String> devTableList = new ArrayList<>();

        ResultSet devResultSet = devStmt.executeQuery(sql);
        while (devResultSet.next()) {
            String tableName = devResultSet.getString(1);
            devTableList.add(tableName);
        }
        List<String> testTableList = new ArrayList<>();
        ResultSet testResultSet = testStatement.executeQuery(sql);
        while (testResultSet.next()) {
            String tableName = testResultSet.getString(1);
            testTableList.add(tableName);
        }

        tableMap.put("devTable",devTableList);
        tableMap.put("testTable",testTableList);

        List<String> tableList = new ArrayList<>();
        tableList.addAll(devTableList);
        tableList.removeAll(testTableList);

        if (CollectionUtils.isNotEmpty(tableList)){
            createTableListMethod(tableList);
        }
    }

    private static void createTableListMethod(List<String> tableList) throws SQLException {
        String sql = "show create table ";
        for (String tableName: tableList) {
            ResultSet rs = devStmt.executeQuery(sql+tableName);
            if(rs.next()){
                createTableList.add(rs.getString(2));
            }
        }
    }



}
