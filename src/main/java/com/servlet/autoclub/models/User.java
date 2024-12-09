package com.servlet.autoclub.models;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Date;

public class User {
    public int id;
    public String username;
    public String password;
    public String email;
    public Date join_date;


    private static ResultSet ExecuteSQL(String sql) {
        Database.Connect();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = Database.conn.createStatement();
            statement.executeQuery(sql);
            return statement.getResultSet();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static boolean GetResult(String sql) {
        ResultSet resultSet = ExecuteSQL(sql);
        try {
            if(resultSet.next())
                return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public static boolean LoginUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
        return GetResult(sql);
    }
    public static boolean IsEmailExist(String email) {
        String sql = "SELECT * FROM users WHERE email = '" + email + "'";
        return GetResult(sql);
    }
    public static boolean IsUsernameExist(String username) {
        String sql = "SELECT * FROM users WHERE username = '" + username + "'";
        return GetResult(sql);
    }
    public static boolean RegisterUser(String username, String password, String email) {
        if(!IsUsernameExist(username) && !IsEmailExist(email)) {
            String sql = "INSERT INTO users(username, password, email) VALUES ('" + username + "', '" + password + "', '" + email + "')";
            Database.Connect();
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Statement statement = Database.conn.createStatement();
                statement.executeUpdate(sql);
                return true;
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
