<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>작성하기</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>

    <style>
      * {
        box-sizing: border-box;
      }
      div {
        border: 1px solid black;
      }
      .container {
        width: 800px;
        height: 600px;
        margin: auto;
        display: flex;
        flex-direction: column;
      }
      .container .top {
        display: flex;
        justify-content: center;
        align-items: center;
      }
      .container .title {
        flex: 1;
        display: flex;
        align-items: center;
      }
      .container .title > input {
        height: 90%;
        width: 70%;
      }
      .container .body {
        flex: 12;
        display: flex;
        flex-direction: column;
      }
      .container .body .contents {
        flex: 9;
        height: 100%;
      }
      .container .body .file {
        flex: 1;
        display: flex;
        align-items: center;
      }
      .container .footer {
        flex: 1;
        display: flex;
        justify-content: flex-end;
        align-items: center;
      }
    </style>
  </head>
  <body>
    <form
      action="/board/insert"
      method="post"
      id="submit"
      enctype="multipart/form-data"
    >
      <div class="container">
        <div class="top"><p>작성하기</p></div>

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
          <div class="file">
            <input type="file" name="files" multiple />
          </div>
        </div>
        <div class="footer">
          <button type="button" id="tolist">목록으로</button>
          <button type="submit" id="ok">작성완료</button>
        </div>

        <input type="hidden" name="contents" class="hidden_contents" />
      </div>
    </form>

    <script>
      $("#tolist").on("click", function () {
        location.href = "/board/list";
      });

      $("#ok").on("click", function (e) {
        e.preventDefault();
        $(".hidden_contents").val($(".contents").text().trim());
        $("#submit").submit();
      });
    </script>
  </body>
</html>
