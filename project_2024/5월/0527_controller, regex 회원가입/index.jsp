<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Index</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
  </head>

  <body>
    <table border="1" align="center">
      <tr>
        <th>Login</th>
      </tr>
      <tr>
        <td><input type="text" placeholder="input your id" name="id" /></td>
      </tr>
      <tr>
        <td><input type="password" placeholder="input your pw" name="pw" /></td>
      </tr>
      <tr>
        <td align="center">
          <button>Login</button>
          <button id="signup">SignUp</button>
        </td>
      </tr>
    </table>

    <script>
      $("#signup").on("click", function () {
        location.href = "/members/signup.jsp";
      });
    </script>
  </body>
</html>
