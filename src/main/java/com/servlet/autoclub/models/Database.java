package com.servlet.autoclub.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    static String url = "jdbc:mysql://localhost:3306/carsclub";
    static String username = "root";
    static String password = "";
    public static Connection conn;

    public static void Connect() {
        try {
            if(IsConnected())
                return;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            String createUsersTable = "CREATE TABLE IF NOT EXISTS users(" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "username VARCHAR(30) NOT NULL, " +
                    "password VARCHAR(30) NOT NULL, " +
                    "email VARCHAR(30) NOT NULL, " +
                    "join_date DATE NOT NULL DEFAULT CURRENT_TIMESTAMP)";
            String createCarsTable = "CREATE TABLE IF NOT EXISTS cars(" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "user_id INT NOT NULL, FOREIGN KEY (user_id) REFERENCES users(id), " +
                    "name VARCHAR(30) NOT NULL, " +
                    "model VARCHAR(30) NOT NULL, " +
                    "year INT NOT NULL, " +
                    "fuel_type VARCHAR(30) NOT NULL)";
            String createCommentsTable = "CREATE TABLE IF NOT EXISTS comments(" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "car_id INT NOT NULL, FOREIGN KEY (car_id) REFERENCES cars(id), " +
                    "user_id INT NOT NULL, FOREIGN KEY (user_id) REFERENCES users(id), " +
                    "comment_text VARCHAR(500) NOT NULL," +
                    "created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP)";
            CreateTable(createUsersTable);
            CreateTable(createCarsTable);
            CreateTable(createCommentsTable);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    protected static void CreateTable(String query) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) { throw new RuntimeException(e); }
    }
    public static boolean IsConnected() throws ClassNotFoundException, SQLException {
        return conn != null && !conn.isClosed();
    }
    public static void Disconnect() throws SQLException { conn.close(); }
}
