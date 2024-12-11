package com.servlet.autoclub.models;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Comment {
    public int id;
    public int car_id;
    public int user_id;
    public String comment_text;
    public Date created_at;
    public User user;

    public Comment(int id, int car_id, int user_id, String comment_text, Date created_at) {
        this.id = id;
        this.car_id = car_id;
        this.user_id = user_id;
        this.comment_text = comment_text;
        this.created_at = created_at;
        this.user = User.GetUser(user_id);
    }
    public static List<Comment> GetComments(int car_id) {
        List<Comment> comments = new ArrayList<>();
        String selectComments = "SELECT * FROM comments WHERE car_id = " + car_id;
        ResultSet commentsSet = Database.GetResultSetFromSQL(selectComments);
        try {
            while (commentsSet.next()) {
                Comment comment = new Comment(
                        commentsSet.getInt("id"),
                        commentsSet.getInt("car_id"),
                        commentsSet.getInt("user_id"),
                        commentsSet.getString("comment_text"),
                        commentsSet.getTimestamp("created_at")
                );
                comments.add(comment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return comments;
    }
    public static boolean AddComment(Comment comment) {
        String addCar = "INSERT INTO comments(car_id, user_id, comment_text, created_at) VALUES (" + comment.car_id + ", " + comment.user_id + ", '" + comment.comment_text + "', '" + comment.created_at + "')";
        return Database.InsertFromSQL(addCar);
    }
}
