<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
      .container {
        display: flex;
        flex-direction: column;
        width: 400px;
        height: 500px;
        border: 1px solid #ccc;
        border-radius: 10px;
        overflow: hidden;
        background-color: white;
      }
      .messages {
        flex: 1;
        padding: 20px;
        overflow-y: auto;
        border-bottom: 1px solid #ccc;
        background-color: #fff2f4;
      }
      .message {
        margin-bottom: 15px;
	    background-color: white;
	    padding: 10px;
	    border-radius: 10px;
	    max-width: 70%;
      }
      .message .username {
        font-weight: bold;
      } 
      .input {
        padding: 15px;
        border-top: 1px solid #ccc;
        background-color: #ededed;
      }
      .input[contenteditable="true"] {
        outline: none;
      }
</style>
</head>
<body>
	<div class="container">
		<div class="messages">
		</div>
		<div class="input" contenteditable="true">
		</div>
	</div>
	<input type="hidden" id="loginId" value="${loginId}">
	
    <script>

		$(".input").on("keydown", function (e) {
			if (e.key == "Enter") {
		  		let text = $(".input").html();
		    	ws.send(text);
		    	 console.log($("#loginId").val() );
			    $(".input").html("");
		    	return false;
		  }
		});
    
    
		let ws = new WebSocket("ws://192.168.1.15/chat");
		ws.onmessage = function (e) {
			let messages = $(".messages");
	/* 		let username = $("#loginId").val(); */
			let message = $("<div>");
			let line = $("<div>");
		
			message.addClass("message");
			/* username.addClass("username"); */
		 	/* message.append($("#loginId").val() + ":" + e.data); */
		 	message.append(e.data);

			
			line.append(message);
			messages.append(line);
			
			messages.scrollTop(messages.prop("scrollHeight"));
		};
		

    </script>
</body>
</html>