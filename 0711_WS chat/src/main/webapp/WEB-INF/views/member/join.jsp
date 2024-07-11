<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Join Form</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
  </head>
  <body>
    <form action="/member/joinProc">
      <table border="1" align="center">
        <tr>
          <th>회원가입</th>
        </tr>
        <tr>
          <td>
            <input type="text" name="id" id="id" placeholder="input your id" />
            <div id="result"></div>
          </td>
        </tr>
        <tr>
          <td>
            <input type="password" name="pw" placeholder="input your pw" />
          </td>
        </tr>
        <tr>
          <td>
            <input type="text" name="name" placeholder="input your name" />
          </td>
        </tr>
        <tr>
          <td align="center"><button>Join</button></td>
        </tr>
      </table>
    </form>

    <script>
      $("#id").on("input", (e) => {
        // console.log(e.target.value); // = this

        $.ajax({
          url: "/member/idcheck",
          data: { id: e.target.value },
        }).done((resp) => {
        	
          if (resp=="true") {
            $("#result").html("이미 사용중인 ID입니다.");
          } else {
            $("#result").html("사용 가능한 ID입니다.");
          }
        });
      });
    </script>
  </body>
</html>
