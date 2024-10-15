<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	

	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie List</title>
</head>
<body>

	<table border=1>
		<tr>
			<th colspan=5>Movies
		</tr>
		<tr>
			<th>Id</th>
			<th>Title</th>
			<th>Genre</th>
			<th>Date</th>
			<th>Delete</th>
		</tr>		
		<c:forEach var="dto" items="${list}">
			<tr>
				<td>${dto.id }</td>
				<td>${dto.title }</td>
				<td>${dto.genre }</td>
				<td>${dto.movie_date }</td>
				<td><button onclick="location.href='DeleteServlet?id=${dto.id}'">X</button></td>
			</tr>
		</c:forEach>
		
		<tr>
			<td colspan=5>
				<form action='/DeleteServlet'>
					<input type='text' placeholder='삭제할 ID' name='id'>
					<button id='delete_btn'>Delete</button>
				</form>
			</td>
		</tr>
		<tr>
			<td colspan=5>
			<form action='/UpdateServlet' id='update'>
				<input type='text' placeholder='수정 대상의 Id' name='update_id' id='update_id'> <br>
				<input type='text' placeholder='수정할 Title' name='update_title' id='update_title'> <br>
				<input type='text' placeholder='수정할 Genre' name='update_genre' id='update_genre'> <br>
				<button id='update_btn'>Update</button>
			</form>
			</td>
		</tr>
		<tr>
			<td colspan=5 align=center>
				<button id='back_btn'>Back</button>
			</td>
		</tr>		
	</table>
	<script>
		document.getElementById('back_btn').onclick = function () {
			location.href = '/index.html';
		}
		
		document.getElementById('update').onsubmit = function (e) {
			if(document.getElementById('update_id').value == ""){
				alert("id 입력");
				e.preventDefault();
				return false;
			}
			if(document.getElementById('update_title').value == ""){
				alert("title 입력");
				e.preventDefault();
				return false;
			}
			if(document.getElementById('update_genre').value == ""){
				alert("genre 입력");
				e.preventDefault();
				return false;
			}

		}
		
		
	</script>


</body>
</html>