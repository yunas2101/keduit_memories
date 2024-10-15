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
    <c:choose>
      <c:when test="${loginID != null}">
        <table border="1" align="center">
          <tr>
            <th>${loginID} 님 환영합니다.</th>
          </tr>
          <tr>
            <td>
              <form action="/memberout.members">
                <button type="button" id="to_board">게시판으로</button>
                <button type="button" id="mypage">내정보</button>
                <button type="button" id="logout">로그아웃</button>
                <button type="submit" id="memberout">회원탈퇴</button>
              </form>
            </td>
          </tr>
        </table>
        <script>
        	  $("#to_board").on("click", function(){
        		  location.href = "/list.board";
        	  })
          
        	  $("#mypage").on("click", function(){
        		  location.href = "/mypage.members";
        	  })
        	  $("#logout").on("click", function(){
        		  location.href = "/logout.members";
        	  })
        	  $("#memberout").on("click", function(){
        		  return confirm("정말 탈퇴하시겠습니까?");
        	  })
        </script>
      </c:when>
      <c:otherwise>
        <form action="/login.members" method="post">
          <table border="1" align="center">
            <tr>
              <th colspan="2">Login Box</th>
            </tr>
            <tr>
              <td>아이디</td>
              <td>
                <input type="text" placeholder="Input your ID" name="id" />
              </td>
            </tr>
            <tr>
              <td>패스워드</td>
              <td>
                <input type="text" placeholder="Input your PW" name="pw" />
              </td>
            </tr>
            <tr>
              <td colspan="2" align="center">
                <button type="submit">로그인</button>
                <button type="button" id="signup">회원가입</button>
              </td>
            </tr>
          </table>
        </form>
	    <script>
	      $("#signup").on("click", function () {
	        location.href = "/members/signup.jsp";
	      });
	    </script>
      </c:otherwise>
    </c:choose>
  </body>
</html>
