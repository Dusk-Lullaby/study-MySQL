package com.lullaby.jdbc;

import java.sql.*;
import java.util.Scanner;

public class PreparedStatementTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入商品名称:");
        String goodsName = scanner.nextLine();

        String url = "jdbc:mysql://localhost:3306/lesson?serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "root";
        try {
            // 加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 获取连接
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "SELECT id, name, number, price, agent_id FROM goods WHERE name= ? LIMIT 0, 20";
            // 创建预处理执行器
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // 设置占位符替换的值
            preparedStatement.setString(1, goodsName);
            // 使用执行器执行查询，得到一个结果集
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                int number = resultSet.getInt("number");
                double price = resultSet.getDouble("price");
                long agentId = resultSet.getLong("agent_id");
                System.out.println(id + ", " + name + ", " + number + ", " + price + ", " + agentId);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static void sqlInjection() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入商品名称:");
        String goodsName = scanner.nextLine();
        goodsName = "'" + goodsName + "'";

        String url = "jdbc:mysql://localhost:3306/lesson?serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "root";
        try {
            // 加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 获取连接
            Connection connection = DriverManager.getConnection(url, username, password);
            // 在连接上创建SQL语句执行器
            Statement statement = connection.createStatement();
            // 输入： 小米10' or 1='1
            // 输入结果："SELECT id, name, number, price, agent_id FROM goods WHERE name =" + "'" + 小米10' or 1='1 + "'" + " LIMIT 0, 20"
            // SQL注入
            String sql = "SELECT id, name, number, price, agent_id FROM goods WHERE name =" + goodsName + " LIMIT 0, 20";
            // 使用执行器执行查询，得到一个结果集
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                int number = resultSet.getInt("number");
                double price = resultSet.getDouble("price");
                long agentId = resultSet.getLong("agent_id");
                System.out.println(id + ", " + name + ", " + number + ", " + price + ", " + agentId);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
