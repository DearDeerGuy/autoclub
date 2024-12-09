package com.servlet.autoclub.controllers;

import com.servlet.autoclub.models.Database;
import com.servlet.autoclub.models.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "usersServlet", value = "/users")
public class UsersServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Database.Connect();
        if(User.RegisterUser("andrey", "123456", "andrey@gmail.com"))
            out.println("SUCCESSFULLY REGISTERED");
        else
            out.println("USER EXISTS");
        if(User.LoginUser("andrey", "123456"))
            out.println("SUCCESSFULLY LOGIN");
        else
            out.println("WRONG USER DATA");
        out.println("<html><body>");
        out.println("</body></html>");
    }
}
