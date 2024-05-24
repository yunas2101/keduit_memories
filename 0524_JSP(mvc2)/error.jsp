<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:if test="${result == 1}">
		올바른 값을 입력하세요
	<button onclick="location.href='/inputForm.html'">뒤로가기</button>
	</c:if>
	
	<c:if test="${result == 2}">
		데이터가 올바르지 않습니다.
	<button onclick="location.href='/OutputServlet'">목록가기</button>
	</c:if>

	<c:if test="${result == 3}">
		데이터가 존재하지 않습니다.
	<button onclick="location.href='/OutputServlet'">목록가기</button>
	</c:if>

</body>
</html>