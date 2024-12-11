package com.servlet.autoclub.models;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class User {
    public int id;
    public String username;
    public String password;
    public String email;
    public Date join_date;

    public static boolean LoginUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
        return Database.GetResult(sql);
    }
    public static boolean IsEmailExist(String email) {
        String sql = "SELECT * FROM users WHERE email = '" + email + "'";
        return Database.GetResult(sql);
    }
    public static boolean IsUsernameExist(String username) {
        String sql = "SELECT * FROM users WHERE username = '" + username + "'";
        return Database.GetResult(sql);
    }
    public static boolean RegisterUser(String username, String password, String email) {
        if(IsUsernameExist(username) || IsEmailExist(email))
            return false;
        String sql = "INSERT INTO users(username, password, email) VALUES ('" + username + "', '" + password + "', '" + email + "')";
        return Database.InsertFromSQL(sql);
    }
    public static boolean GetAuthorizationFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return false;
        String login = "", password = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login"))
                login = cookie.getValue();
            if(cookie.getName().equals("password"))
                password = cookie.getValue();
        }
        if(login.isEmpty() || password.isEmpty())
            return false;
        return LoginUser(login, password);
    }
    public static void AddUserToCookie(HttpServletResponse response, String login, String password) {
        Cookie cookieLogin = new Cookie("login", login);
        cookieLogin.setMaxAge(60 * 60 * 24);
        response.addCookie(cookieLogin);

        Cookie cookiePassword = new Cookie("password", password);
        cookiePassword.setMaxAge(60 * 60 * 24);
        response.addCookie(cookiePassword);
    }
    public static void Logout(HttpServletResponse response) {
        Cookie cookieLogin = new Cookie("login", "");
        cookieLogin.setMaxAge(0);
        response.addCookie(cookieLogin);

        Cookie cookiePassword = new Cookie("password", "password");
        cookiePassword.setMaxAge(0);
        response.addCookie(cookiePassword);
    }
    public static User ParseUserFromSet(ResultSet userSet) {
        try {
            if (userSet.next()) {
                User user = new User();
                user.id = userSet.getInt("id");
                user.username = userSet.getString("username");
                user.password = userSet.getString("password");
                user.email = userSet.getString("email");
                user.join_date = userSet.getDate("join_date");
                return user;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static User GetUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
        ResultSet userSet = Database.GetResultSetFromSQL(sql);
        return  ParseUserFromSet(userSet);
    }
    public static User GetUser(int id) {
        String sql = "SELECT * FROM users WHERE id = " + id;
        ResultSet userSet = Database.GetResultSetFromSQL(sql);
        return  ParseUserFromSet(userSet);
    }
}