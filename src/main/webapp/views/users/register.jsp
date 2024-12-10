<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
  <form id="myForm" method="post">
    <input type="hidden" name="action" value="register" />
    <label>Email
      <input name="email" type="email">
    </label>
    <label>Username
      <input name="username" type="text">
    </label>
    <label>Password
      <input name="password" type="password">
    </label>
    <button type="submit">Register</button>
  </form>
  <a href="users?action=login"><button>Login</button></a>
</body>
</html>