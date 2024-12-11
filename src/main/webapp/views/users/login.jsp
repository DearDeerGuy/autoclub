<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
</head>
<body>
    <main>
        <form method="post" class="authform">
            <h1>LOGIN</h1>
            <input type="hidden" name="action" value="login" />
            <label>Username
                <input name="username" type="text" required>
            </label>
            <label>Password
                <input name="password" type="password" required>
            </label>
            <div class="buttons">
                <button type="submit">Login</button>
                <span>Don't have account? <a href="users?action=register">Register</a></span>
            </div>
        </form>
    </main>
</body>
</html>

