package com.mmall.dao;

import java.sql.*;
import java.util.*;


public class test {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/mmall_learning";
    static final String USER="root";
    static final String PASS="mike";
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            //注册JDBC 驱动程序
//            Class.forName("com.mysql.jdbc.Driver");

            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);



            //打开连接
            System.out.println("Connecting to database...");
            Properties pro = new Properties();
            pro.put("user","root");
            pro.put("password","mike");

            conn = DriverManager.getConnection(DB_URL,pro);

            //执行查询
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, quantity, create_time FROM mmall_cart";
            ResultSet rs = stmt.executeQuery(sql);



            //得到和处理结果集
            while(rs.next()){
                //检索
                int id  = rs.getInt("id");
                int age = rs.getInt("quantity");
                String name = rs.getString("create_time");

                //显示
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", Name: " + name);
                System.out.println();
            }
            //清理环境
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // JDBC 操作错误
            se.printStackTrace();
        }catch(Exception e){
            // Class.forName 错误
            e.printStackTrace();
        }finally{
            //这里一般用来关闭资源的
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }

}
