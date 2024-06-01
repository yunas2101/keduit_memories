<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Index</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>

    <style></style>
  </head>

  <body>
    <table border="1" align="center">
      <tr>
        <th colspan="2">Login Box</th>
      </tr>
      <tr>
        <td>아이디</td>
        <td><input type="text" placeholder="Input your ID" /></td>
      </tr>
      <tr>
        <td>패스워드</td>
        <td><input type="text" placeholder="Input your PW" /></td>
      </tr>
      <tr>
        <td colspan="2" align="center">
          <button>로그인</button>
          <button type="button" id="signup">회원가입</button>
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
