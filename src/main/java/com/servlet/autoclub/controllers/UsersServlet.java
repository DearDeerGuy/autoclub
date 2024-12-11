package com.servlet.autoclub.controllers;

import com.servlet.autoclub.models.Database;
import com.servlet.autoclub.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.*;

import java.io.*;

@WebServlet(name = "usersServlet", value = "/users")
public class UsersServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String action = request.getParameter("action") == null ? "login" : request.getParameter("action");
        if(User.GetAuthorizationFromCookies(request) && !action.equals("logout")) {
            response.sendRedirect("/cars");
            return;
        }
        request.setAttribute("action", action);
        switch (action) {
            case "login":
                getServletContext().getRequestDispatcher("/views/users/login.jsp").forward(request, response);
                break;
            case "register":
                getServletContext().getRequestDispatcher("/views/users/register.jsp").forward(request, response);
                break;
            case "logout": {
                User.Logout(response);
                response.sendRedirect("/users");
            }
                break;
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        String action = request.getParameter("action") == null ? "login" : request.getParameter("action");

        if(username.isEmpty() || username == null || password.isEmpty() || password == null || (action.equals("register") && email.isEmpty())) {
            response.sendRedirect("/users");
            return;
        }


        switch(action) {
            case "login":
            {
                if(User.LoginUser(username, password)) {
                    User.AddUserToCookie(response, username, password);
                    response.sendRedirect("/cars");
                    return;
                }
            }
            break;
            case "register":
            {
                if(User.RegisterUser(username, password, email)) {
                    User.AddUserToCookie(response, username, password);
                    response.sendRedirect("/cars");
                    return;
                }
            }
            break;
        }
        getServletContext().getRequestDispatcher("/views/users/login.jsp").forward(request, response);
    }
}