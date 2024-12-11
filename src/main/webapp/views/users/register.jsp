<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
</head>
<body>
    <main>
        <form method="post" class="authform">
            <h1>REGISTER</h1>
            <input type="hidden" name="action" value="register">
            <label>Email
              <input name="email" type="email" required>
            </label>
            <label>Username
              <input name="username" type="text" required>
            </label>
            <label>Password
              <input name="password" type="password" required>
            </label>
            <div class="buttons">
              <button type="submit">Register</button>
              <span>Have an account? <a href="users?action=login">Login</a></span>
            </div>
        </form>
    </main>
</body>
</html>