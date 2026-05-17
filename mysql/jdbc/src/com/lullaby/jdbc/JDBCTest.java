package com.lullaby.jdbc;

import com.mysql.cj.result.BufferedRowList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCTest {

    public static void main(String[] args) {
        // jdbc: 使用jdbc连接技术
        // http://www.baidu.com
        // mysql://localhost:3306 使用的是MySQL数据库协议，访问的是本地计算机端口3306
        String url = "jdbc:mysql://localhost:3306/lesson?serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "root";
        List<Account> accountList = new ArrayList<>();
        // mysql80
        try {
            // 加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 获取连接
            Connection connection = DriverManager.getConnection(url, username, password);
            // 在连接上创建SQL语句执行器
            Statement statement = connection.createStatement();
//            String sql = "SELECT account, balance, state FROM account";
//            // 使用执行器执行查询，得到一个结果集
//            ResultSet resultSet = statement.executeQuery(sql);
//            while (resultSet.next()) {  // 光标移动
//                // 通过列名称获取列的值
//                String account = resultSet.getString("account");
//                double balance = resultSet.getDouble(2);
//                int state = resultSet.getInt("state");
//                Account a = new Account(account, balance, state);
//                accountList.add(a);
//            }
//            resultSet.close();
            String updateSql = "UPDATE account SET balance = balance + 2000 WHERE account = 123456";
            // 执行更新时返回的都是收影响的行数
            int affectedRows = statement.executeUpdate(updateSql);
            System.out.println(affectedRows);
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        accountList.forEach(System.out::println);
    }
}
