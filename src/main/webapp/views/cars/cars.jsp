<%@ page import="com.servlet.autoclub.models.Car" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cars</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/cars.css">
</head>
    <body>
    <header>
        <a href="users?action=logout"><button>Logout</button></a>
    </header>
    <main>

        <form method="get" class="search">
            <input name="search" placeholder="Searching input">
            <button>Search</button>
        </form>
        <%if(!(request.getAttribute("list") == null)) {%>
            <table>
            <%for(Car car: (List<Car>)request.getAttribute("list")) { %>
                <% String search = request.getParameter("search") != null ?request.getParameter("search") : "";
                if(car.toString().toLowerCase().contains(search.toLowerCase())) {%>
                <tr>
                <td><%=car.name%></td>
                <td><%=car.model%></td>
                <td><%=car.year%></td>
                <td><%=car.fuel_type%></td>
                <td><%=car.user.username%></td>
                <td><a href = "cars?action=car&id=<%=car.id%>">Details</a></td>
                </tr>
            <%}}%>
            </table>
        <%}%>
    </main>
</body>
</html>
