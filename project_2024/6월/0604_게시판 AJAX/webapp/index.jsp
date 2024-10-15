<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Index</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>

    <style>
      * {
        box-sizing: border-box;
      }

      .container {
        width: 400px;
        height: 300px;
        margin: auto;
        display: flex;
        flex-direction: column;
        border: 2px solid #908f8f;
        border-radius: 10px;
        padding: 20px;
        box-shadow: 0 0 10px hsla(0, 0%, 0%, 0.1);
      }

      .header {
        flex: 1;
        display: flex;
        justify-content: center;
        align-items: center;
        font-size: x-large;
        font-weight: bold;
        margin-bottom: 20px;
      }

      .body {
        flex: 4;
        display: flex;
        flex-direction: column;
      }

      .row {
        flex: 1;
        margin-bottom: 15px;
      }

      .text {
        flex: 1;
        display: flex;
        align-items: center;
      }

      .input {
        flex: 3;
        display: flex;
      }

      input {
        width: 100%;
        height: 35px;
        border-radius: 5px;
        border: 2px solid #908f8f;
        padding-left: 10px;
      }

      .footer {
        flex: 1;
        display: flex;
        align-items: center;
        justify-content: center;
      }

      .footer button {
        margin: 10px;
        height: 40px;
        min-width: 100px;
        background-color: #313d5a;
        color: rgb(255, 255, 255);
        font-weight: bold;
        border: none;
        border-radius: 5px;
        padding: 0 15px;
        cursor: pointer;
      }

      .footer button#signup {
        background-color: #efca63;
      }

      /* 로그인 id, pw 입력창 */
      .buttons {
        display: flex;
        justify-content: space-between;
      }

      .buttons button {
        flex: 1;
        margin: 5px;
        height: 40px;
        background-color: #313d5a;
        color: rgba(255, 255, 255, 0.941);
        font-weight: bold;
        border: none;
        border-radius: 5px;
        cursor: pointer;
      }

      .buttons #memberout {
        background-color: #da6868;
      }
    </style>
  </head>

  <body>
    <c:choose>
      <c:when test="${loginID != null }">
        <div class="container">
          <div class="header">${loginID} 님 환영합니다.</div>
          <div class="buttons">
            <button id="to_board">게시판으로</button>
            <button id="mypage">내정보</button>
            <button id="logout">로그아웃</button>
            <form action="/memberout.members">
              <button type="submit" id="memberout">회원탈퇴</button>
            </form>
          </div>
        </div>

        <script>
          $("#to_board").on("click", function () {
           /*  location.href = "/board/list.jsp"; */
            location.href = "/list.board";
          });
          $("#mypage").on("click", function () {
            location.href = "/mypage.members";
          });
          $("#logout").on("click", function () {
            location.href = "/logout.members";
          });
          $("#memberout").on("click", function () {
            return confirm("정말 탈퇴하시겠습니까?");
          });
        </script>
      </c:when>

      <c:otherwise>
        <form action="/login.members" method="post">
          <div class="container">
            <div class="header">로그인</div>
            <div class="body">
              <div class="row">
                <div class="text">아이디</div>
                <div class="input">
                  <input
                    type="text"
                    placeholder="아이디 입력"
                    name="id"
                    id="id"
                  />
                </div>
              </div>
              <div class="row">
                <div class="text">패스워드</div>
                <div class="input">
                  <input
                    type="password"
                    placeholder="패스워드 입력"
                    name="pw"
                    id="pw"
                  />
                </div>
              </div>
            </div>
            <div class="footer">
              <button type="submit" id="login">로그인</button>
              <button type="button" id="signup">회원가입</button>
            </div>
          </div>
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
