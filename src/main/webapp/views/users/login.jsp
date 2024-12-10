<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form method="post">
        <input type="hidden" name="action" value="login" />
        <label>Username
            <input name="username" type="text">
        </label>
        <label>Password
            <input name="password" type="password">
        </label>
        <button type="submit">Login</button>
    </form>
    <a href="users?action=register"><button>Register</button></a>
</body>
</html>

