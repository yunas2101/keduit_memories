<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>input view</title>
</head>
<body>
	입력이 완료되었습니다.
	<button id='btn'>OK</button>
	
	<script>
		document.getElementById("btn").onclick = function (){
			location.href = "index.html";
		}
	
	</script>
</body>
</html>