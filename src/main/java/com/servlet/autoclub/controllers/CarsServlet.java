package com.servlet.autoclub.controllers;

import com.servlet.autoclub.models.Car;
import com.servlet.autoclub.models.Comment;
import com.servlet.autoclub.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "carsServlet", urlPatterns = "/cars")
public class CarsServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        if(!User.GetAuthorizationFromCookies(request)) {
            response.sendRedirect("/users");
            return;
        }
        String action = request.getParameter("action") == null ? "list" : request.getParameter("action");
        switch (action) {
            case "add": {

            }
            break;
            case "list": {
                request.setAttribute("list", Car.GetCars());
                getServletContext().getRequestDispatcher("/views/cars/cars.jsp").forward(request, response);
            }
            break;
            case "car": {
                int id = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : -1;
                Car car = new Car(id);
                if(car != null) {
                    request.setAttribute("car", car);
                    List<Comment> comments = Comment.GetComments(car.id);
                    request.setAttribute("comments", comments);
                    getServletContext().getRequestDispatcher("/views/cars/car.jsp").forward(request, response);
                }
                else
                    return;
            }
            break;
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");


    }
}
