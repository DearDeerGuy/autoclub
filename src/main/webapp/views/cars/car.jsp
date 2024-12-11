<%@ page import="com.servlet.autoclub.models.Car" %>
<%@ page import="com.servlet.autoclub.models.Comment" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Car</title>
</head>
<body>
    <%if(request.getAttribute("car") != null) {
        Car car = (Car)request.getAttribute("car"); %>
        <h1><%= car.name %> <%=car.model%></h1>
        <p>Year: <%= car.year %></p>
        <p>Type of fuel: <%= car.fuel_type%></p>
        <p>Owner: <%= car.user.username%></p>
        <% if(request.getAttribute("comments") != null) {
        for(Comment comment: (List<Comment>)request.getAttribute("comments")) {%>
            <h3><%= comment.user.username %> | <%= comment.created_at %></h3>
            <p><%= comment.comment_text %></p>
    <%}}}%>
</body>
</html>
