<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Mypage</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
  </head>
  <body>
    <table border="1" align="center">
      <tr>
        <th colspan="2">Mypage</th>
      </tr>
      <form action="/member/update" method="post" id="updateForm">
        <tr>
          <td>ID :</td>
          <td>${dto.id}</td>
        </tr>
        <tr>
          <td>Name :</td>
          <td class="name">${dto.name}</td>
        </tr>
        <tr>
          <td align="center" colspan="2">
            <button type="button" class="update">수정</button>
            <button type="submit" class="ok" style="display: none">완료</button>
          </td>
        </tr>
      <input type="hidden" id="hidden_name" name="name"/>
      </form>
    </table>
    
    <script>
      $(".update").on("click", function () {
        $(".name").attr("contenteditable", true);

        $(".update").hide();
        $(".ok").show();
      });
      
      $("#updateForm").on("submit",function(){
        $("#hidden_name").val($(".name").text().trim());
    	  
      })
      
    </script>
    
  </body>
</html>
