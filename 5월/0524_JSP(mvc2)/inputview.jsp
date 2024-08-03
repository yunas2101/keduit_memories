<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입력 결과</title>
</head>
<body>
	입력이 완료되었습니다. <button id="ok">OK</button>

	<script>
	document.getElementById('ok').onclick = function () {
			location.href = "index.html";
		}
	</script>

</body>
</html>