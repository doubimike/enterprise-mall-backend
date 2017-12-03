package com.mmall.dao;



        import java.sql.*;

public class jdbcChuli {
    // JDBC 驱动器名称 和数据库地址
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //数据库的名称为 EXAMPLE
    static final String DB_URL = "jdbc:mysql://localhost/EXAMPLE";

    //  数据库用户和密码
    static final String USER = "root";
    static final String PASS = "mike";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            //注册JDBC 驱动程序
            Class.forName("com.mysql.jdbc.Driver");

            //打开连接
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            conn.setAutoCommit(false);

            //执行查询
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            // 关闭自动提交
            conn.setAutoCommit(false);

            // 创建 SQL 语句
            String SQL = "INSERT INTO Students (id, name, age) VALUES(61,'Mike', 21)";
// 将 SQL 语句添加到批处理中
            stmt.addBatch(SQL);

// 创建更多的 SQL 语句
            String SQL2 = "INSERT INTO Students (id, name, age) VALUES(71, 'Angle', 23)";
// 将 SQL 语句添加到 批处理中
            stmt.addBatch(SQL2);


// 创建整数数组记录更新情况
            int[] count = stmt.executeBatch();

//提交更改
            conn.commit();




        }catch(SQLException se){
            // JDBC 操作错误
            se.printStackTrace();
            // conn.rollback();
            try{
                if(conn!=null)
                    conn.rollback();
            }catch(SQLException se2){
                se2.printStackTrace();
            }
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
