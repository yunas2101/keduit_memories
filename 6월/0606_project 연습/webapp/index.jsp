<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Index</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
  </head>
  <body>
    <button id="info_update">1.정보수정</button>
    <button id="gameplay">2.게임 플레이 정보</button>
    <button id="pw_update">3.비번변경</button>
    <button id="community">4.커뮤니티 소셜 기능</button>
    <button id="support">5.고객지원</button>

    <script>
      $("#info_update").on("click", function () {
        location.href = "/select.mypage";
      });
      $("#gameplay").on("click", function () {
        location.href = "/mypage/gameplay.jsp";
      });
      $("#pw_update").on("click", function () {
        location.href = "/mypage/pwUpdate.jsp";
      });
      $("#community").on("click", function () {
        location.href = "/mypage/community.jsp";
      });
      $("#support").on("click", function () {
        location.href = "/mypage/support.jsp";
      });
    </script>
  </body>
</html>
