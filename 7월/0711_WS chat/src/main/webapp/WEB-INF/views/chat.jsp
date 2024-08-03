<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>채팅</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <style>
      body {
        font-family: Arial, sans-serif;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
        background-color: #f0f0f0;
      }
      .wrap {
        display: flex;
      }
      .container {
        display: flex;
        flex-direction: column;
        width: 600px;
        height: 800px;
        border: 1px solid #ccc;
        border-radius: 10px;
        overflow: hidden;
        background-color: white;
      }
      .output {
        flex: 9;
        padding: 20px;
        overflow-y: auto;
        border-bottom: 1px solid #ccc;
        background-color: #fff2f4;
      }
      .line {
        display: flex;
      }

      .chat_box {
        display: flex;
        flex-direction: column;
      }
      .username {
        flex: 1;
        font-weight: bold;
        margin-top: 15px;
      }
      .chat {
        flex: 2;
        background-color: white;
        margin-top: 5px;
        padding: 10px;
        border-radius: 10px;
        max-width: 350px;
      }

      .time {
        display: flex;
        align-items: flex-end;
        margin-left: 10px;
      }

      .input {
        flex: 1;
        padding: 15px;
        border-top: 1px solid #ccc;
        background-color: #ededed;
        height: 100px; /* 고정 높이 설정 */
        overflow-y: auto; /* 스크롤 가능하게 설정 */
      }
      .input[contenteditable="true"] {
        outline: none;
      }
      /* .wrap > div:last-child {
		display: flex;
		flex-direction: column;
		margin-left: 10px;

	  } */
      .img_box {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: flex-start;
        width: 200px;
        height: 700px;
        background-color: #ffffffc2;
        border-radius: 10px;
      }
      .img_box img {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-content: center;
        width: 50%;
        cursor: pointer;
      }

      #home {
        flex: 1;
        background-color: #f7c3c3;
        color: white;
        font-weight: bolder;
        font-size: larger;
        border: none;
        border-radius: 10px;
        margin-top: 10px;
        cursor: pointer;
      }
    </style>
  </head>
  <body>
    <div class="wrap">
      <div class="container">
        <div class="output">
          <c:forEach var="c" items="${chat }">
            <div class="line">
              <div class="chat_box">
                <div class="username">${c.sender }</div>
                <div class="chat">${c.message }</div>
              </div>
              <div class="time">${c.write_date }</div>
            </div>
          </c:forEach>
        </div>
        <div class="input" contenteditable="true"></div>
      </div>
      <div style="display: flex; flex-direction: column; margin-left: 10px">
        <div class="img_box">
          <img src="/resources/images/img1.png" />
          <img src="/resources/images/img2.png" />
          <img src="/resources/images/img3.png" />
          <img src="/resources/images/img4.png" />
          <img src="/resources/images/img5.png" />
          <img src="/resources/images/img6.png" />
          <img src="/resources/images/img7.png" />
        </div>
        <button id="home" onclick="location.href='/'">홈으로</button>
      </div>
    </div>
    <script>
      $(document).ready(function () {
        $(".input").on("keydown", function (e) {
          if (e.key == "Enter") {
            let text = $(".input").html();
            ws.send(text);

            $(".input").html("");
            return false;
          }
        });

        /* 		let ws = new WebSocket("ws://192.168.1.15/chat"); */
        let ws = new WebSocket("ws://localhost/chat");
        ws.onmessage = function (e) {
          let data = JSON.parse(e.data);

          let output = $(".output");
          let chat_box = $("<div>");
          chat_box.addClass("chat_box");

          let username = $("<div>");
          username.addClass("username");
          username.append(data.sender);

          let chat = $("<div>");
          chat.addClass("chat");
          chat.append(data.message);

          let time = $("<div>");
          time.addClass("time");
          time.append(getCurrentTime());

          chat_box.append(username, chat);

          let line = $("<div>");
          line.addClass("line");
          line.append(chat_box, time);
          output.append(line);

          output.scrollTop(output.prop("scrollHeight"));
        };

        function getCurrentTime() {
          let now = new Date();
          let hours = now.getHours().toString().padStart(2, "0");
          let minutes = now.getMinutes().toString().padStart(2, "0");
          return hours + ":" + minutes;
        }

        $("img").each(function (index, e) {
          $(e).on("click", function () {
            let clone = $(e).clone();
            clone.attr("class", "img_clone");
            $(".input").append(clone);
          });
        });
      });
    </script>
  </body>
</html>
