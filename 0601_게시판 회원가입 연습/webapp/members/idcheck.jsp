<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>ID 중복확인</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
  </head>
  <body>
    <table border="1" align="center">
      <tr>
        <th>아이디 중복확인 결과</th>
      </tr>
      <c:choose>
        <c:when test="${result }">
          <tr>
            <td>이미 사용 중인 ID입니다.</td>
          </tr>
          <tr>
            <td>
              <button id="close">닫기</button>
            </td>
          </tr>
        </c:when>
        <c:otherwise>
          <tr>
            <td align="center">
                사용 가능한 ID입니다. <br>
                정말 사용하시겠습니까?
            </td>
          </tr>
          <tr>
            <td align="center">
                <button id="use">사용</button>
                <button id="cancel">취소</button>
            </td>
          </tr>
        </c:otherwise>
      </c:choose>
    </table>

    <script>
        $("#close").on("click", function () {
            opener.$("#id").val("");
            window.close();
        });
        $("#use").on("click", function(){
            window.close();
            opener.didIdCheck = true;
        })
        $("#cancel").on("click", function(){
            opener.$("#id").val("");
            window.close();
        })

    </script>
  </body>
</html>
