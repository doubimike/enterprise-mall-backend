package com.mmall.dao;

import java.sql.*;

public class ResultSetTest {
    // JDBC 驱动器名称 和数据库地址
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //数据库的名称为 EXAMPLE
    static final String DB_URL = "jdbc:mysql://localhost/EXAMPLE";

    // 数据库用户和密码
    static final String USER = "root";
    static final String PASS = "mike";

    public static void main(String[] args){
        Connection conn = null;
        Statement stmt = null;

        try {
            //注册JDBC 驱动程序
            Class.forName("com.mysql.jdbc.Driver");

            //打开连接
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);



            System.out.println("Creating statement...");

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            String sql;
            sql = "SELECT id,name,age from Students";
            ResultSet rs = stmt.executeQuery(sql);

            // 将光标移到最后一行
            System.out.println("Moving cursor to the last...");
            rs.last();

            //处理结果集
            System.out.println("Displaying record...");
            int id  = rs.getInt("id");
            int age = rs.getInt("age");
            String name = rs.getString("name");


            //显示
            System.out.print("ID: " + id);
            System.out.print(", Age: " + age);
            System.out.print(", Name: " + name);
            System.out.println();

            // 将光标移到第一行
            System.out.println("Moving cursor to the first row...");
            rs.first();


            System.out.println("Displaying record...");
            id  = rs.getInt("id");
            age = rs.getInt("age");
            name = rs.getString("name");


            //显示
            System.out.print("ID: " + id);
            System.out.print(", Age: " + age);
            System.out.print(", Name: " + name);

            //将光标移至下一行
            System.out.println("Moving cursor to the next row...");
            rs.next();


            System.out.println("Displaying record...");
            id  = rs.getInt("id");
            age = rs.getInt("age");
            name = rs.getString("name");

            // 显示
            System.out.print("ID: " + id);
            System.out.print(", Age: " + age);
            System.out.print(", Name: " + name);

            Statement stmt2 = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            String sql2 = "SELECT id, name, age FROM Students";
            ResultSet rs2 = stmt2.executeQuery(sql);

//结果集中插入新行
            rs2.moveToInsertRow();
            rs2.updateInt("id",5);
            rs2.updateString("name","John");
            rs2.updateInt("age",21);
//更新数据库
            rs2.insertRow();


            rs.close();
            stmt.close();
            conn.close();



        }catch (SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(stmt!=null)
                    stmt.close();
            }catch (SQLException se2){

            }
            try{
                if(conn!=null)
                    conn.close();
            }catch (SQLException se){
                se.printStackTrace();
            }
        }


    }

}
