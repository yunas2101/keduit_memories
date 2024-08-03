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
  body {
    font-family: Arial, sans-serif;
    background-color: #f0f0f0;
    margin: 0;
    padding: 0;
  }
  .container {
    width: 800px;
    margin: auto;
    background-color: #fff;
    border: 1px solid #ccc;
    border-radius: 5px;
    padding: 20px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  }
  .top {
    text-align: center;
    margin-bottom: 20px;
  }
  .top p {
    background-color: #ecc4c4;
    color: white;
    padding: 20px;
    margin: 0;
    font-size: x-large;
  }
  .title input[type="text"] {
    width: 100%;
    padding: 10px;
    font-size: 1.2em;
    border: 1px solid #ccc;
    border-radius: 3px;
  }
  .body .contents {
    width: 100%;
    min-height: 300px;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 3px;
    font-size: 1.1em;
    line-height: 1.6;
    outline: none; /* Remove default focus outline */
    white-space: pre-wrap; /* Preserve line breaks and spaces */
    background-color: #fff;
    resize: vertical; /* Allow vertical resizing */
  }
  .body .file input[type="file"] {
    margin-top: 10px;
  }
  .footer {
    margin-top: 20px;
    text-align: right;
  }
  .footer button {
    padding: 10px 20px;
    font-size: 1em;
    cursor: pointer;
    border: none;
    border-radius: 3px;
    background-color: #ecc4c4;
    color: white;
    transition: background-color 0.3s;
  }
  .footer button:hover {
    background-color: #edd1d1;
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
