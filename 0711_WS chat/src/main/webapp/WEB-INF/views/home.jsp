<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
</head>
<body>
	
	
	<c:choose>
		<c:when test="${loginID != null }">
			<table border="1" align="center">
				<tr>
					<th colspan="5">${loginID } 님 환영합니다.
				</tr>
				<tr>
					<td colspan="5">
			        <img src="/resources/img.jpg" width="430px">
			        </td>
			    </tr>
				<tr>
					<td><button type="button" onclick="location.href='/chat/chatList'">채팅</button></td>
					<td><button type="button" onclick="location.href='/board/list'">게시판</button></td>
					<td><button type="button" onclick="location.href='/member/mypage'">마이페이지</button></td>
					<td><button type="button" onclick="location.href='/member/logout'">로그아웃</button></td>
					<td><button type="button" onclick="location.href='/member/signout'">회원탈퇴</button></td>
				</tr>
			</table>
		</c:when>
	
		<c:otherwise>
			<form action="/member/login" method="post">
				<table border="1" align="center">
					<tr>
						<th>Login
					</tr>
					<tr>
						<td><input type="text" name="id" placeholder="input your id">
					</tr>
					<tr>
						<td><input type="password" name="pw" placeholder="input your pw">
					</tr>
					<tr>
						<td align="center">
							<button>Login</button>
							<a href="/member/join"><button type="button">Join</button></a>
						</td>
					</tr>
				</table>
			</form>
	</c:otherwise>
	</c:choose>
</body>
</html>