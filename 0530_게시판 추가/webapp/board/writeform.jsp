<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Write Form</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>

    <style>
      * {
        box-sizing: border-box;
      }
      div {
        border: 1px solid rgb(219, 219, 219);
      }

      .wrap {
        border: none;
      }
      .container {
        /* border: 2px solid black; */
        width: 800px;
        height: 600px;
        margin: auto;
        display: flex;
        flex-direction: column;
      }

      /* top */
      .container .top {
        flex: 1;
        display: flex;
        justify-content: center;
        align-items: center;
        background-color: #a79c8e;
        color: white;
        font-weight: bold;
      }

      /* header */
      .container .title {
        flex: 1;
        display: flex;
        padding: 2px;
      }

      .container .header .title input {
        width: 100px;
      }

      /* body */
      .container .body {
        flex: 12;
        padding: 2px;
      }
      .container .body .contents {
        width: 100%;
        height: 100%;
      }

      [contenteditable="true"]:empty:before {
        content: attr(placeholder);
        color: gray;
      }

      /* footer */
      .container .footer {
        flex: 1;
        display: flex;
        justify-content: flex-end;
        align-items: center;
        height: 50px;
      }
      .container .footer #tolist,
      #ok {
        height: 30px;
        margin-right: 5px;
        background-color: #eb9f9f;
        color: white;
        border: none;
        border-radius: 8px;
        cursor: pointer;
      }
    </style>
  </head>
  <body>
    <form action="/write.board" method="post">
      <div class="wrap">
        <div class="container">
          <div class="top">
            <label for="top">자유 게시판 글 작성하기</label>
          </div>
          <div class="title">
            <input
              type="text"
              id="title"
              name="title"
              placeholder="글 제목을 입력하세요."
            />
          </div>
          <div class="body">
            <div
              class="contents"
              contenteditable="true"
              placeholder="글 내용을 입력하세요"
            ></div>
          </div>
          <div class="footer">
            <button type="button" id="tolist">목록으로</button>
            <button type="submit" id="ok">작성완료</button>
          </div>
        </div>
      </div>
      <input
        type="hidden"
        name="contents"
        id="hidden_contents"
        value="${dto.contents}"
      />
    </form>

    <script>
      $("#tolist").on("click", function () {
        location.href = "/list.board";
      });
      $("#ok").on("click", function () {
        $("#hidden_contents").val($(".contents").text().trim());
      });
    </script>
  </body>
</html>
