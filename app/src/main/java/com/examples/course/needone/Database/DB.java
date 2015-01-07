package com.examples.course.needone.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.pool.OracleDataSource;

public class DB {

    private final String username = "ww2383";
    private final String password = "wwx";
    private Connection conn = null;
    public static Statement stmt;//define statement
    public ResultSet rs;//set result



    public DB() {

        try {

            OracleDataSource ods = new oracle.jdbc.pool.OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@//w4111f.cs.columbia.edu:1521/ADB");
            ods.setUser(username);
            ods.setPassword(password);
            conn = ods.getConnection();
        }

        catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }

        if (conn == null) {
            System.out.println("Failed to make connection to the database!");

        }
        else
            System.out.println("Success to make connection to the database!");

    }

    public Connection getConnection() {
        return conn;
    }


    //do insert
    public void doInsert(String sql) {
        try {
            stmt = conn.createStatement();
            int i = stmt.executeUpdate(sql);
        } catch(SQLException sqlexception) {
            System.err.println("db.executeInset:" + sqlexception.getMessage());
        }finally{

        }
    }
    //do delete
    public void doDelete(String sql) {
        try {
            stmt = conn.createStatement();
            int i = stmt.executeUpdate(sql);
        } catch(SQLException sqlexception) {
            System.err.println("db.executeDelete:" + sqlexception.getMessage());
        }
    }
    //do update
    public void doUpdate(String sql) {
        try {
            stmt = conn.createStatement();
            int i = stmt.executeUpdate(sql);
        } catch(SQLException sqlexception) {
            System.err.println("db.executeUpdate:" + sqlexception.getMessage());
        }
    }
    //do select
    public ResultSet doSelect(String sql) {
        try {
            stmt = conn.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,java.sql.ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sql);
            System.out.println("Get Result!");
        } catch(SQLException sqlexception) {
            System.err.println("db.executeQuery: " + sqlexception.getMessage());
        }
        return rs;
    }

    public void close(ResultSet rs) throws SQLException, Exception {

        if (rs != null) {
            rs.close();
            rs = null;
        }

        if (stmt != null) {
            stmt.close();
            stmt = null;
        }

        if (conn != null) {
            conn.close();
            conn = null;
        }
    }


    public void close() throws SQLException, Exception {
        if (stmt != null) {
            stmt.close();
            stmt = null;
        }

        if (conn != null) {
            conn.close();
            conn = null;
        }
    }
    public static void main(String []args){
        DB db=new DB();
    }
}