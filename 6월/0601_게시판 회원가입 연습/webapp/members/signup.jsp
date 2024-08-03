<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>

    <style>
      table {
        width: 400px;
        height: 450px;
      }

      input {
        height: 70%;
      }
    </style>
  </head>
  <body>
    <form action="/signup.members" id="joinform">
      <table border="1" align="center">
        <tr>
          <th colspan="2">회원가입</th>
        </tr>
        <tr>
          <td>아이디</td>
          <td>
            <input type="text" placeholder="아이디" id="id" name="id"/>
            <button type="button" id="id_check">중복확인</button>
          </td>
        </tr>
        <tr>
          <td id="correct_id" colspan="2"></td>
        </tr>
        <tr>
          <td>패스워드</td>
          <td><input type="password" placeholder="패스워드" id="pw"  name="pw"/></td>
        </tr>
        <tr id="tr_repw">
          <td>패스워드 재확인</td>
          <td>
            <input type="password" placeholder="패스워드 재입력" id="repw" />
          </td>
        </tr>
        <tr>
          <td id="correct_repw" colspan="2"></td>
        </tr>
        <tr id="tr_name">
          <td>이름</td>
          <td><input type="text" placeholder="이름" id="name"  name="name"/></td>
        </tr>
        <tr>
          <td>전화번호</td>
          <td><input type="text" placeholder="전화번호" id="phone"  name="phone"/></td>
        </tr>
        <tr>
          <td>이메일</td>
          <td><input type="text" placeholder="이메일" id="email"  name="email"/></td>
        </tr>
        <tr>
          <td>우편번호</td>
          <td>
            <input type="text" placeholder="우편번호" id="post" name="post"/>
            <button>검색</button>
          </td>
        </tr>
        <tr>
          <td>주소1</td>
          <td><input type="text" placeholder="주소1" id="add1"  name="add1"/></td>
        </tr>
        <tr>
          <td>주소2</td>
          <td><input type="text" placeholder="주소2" id="add2"  name="add2"/></td>
        </tr>
        <tr>
            <td colspan="2" align="center">

                		<button type="submit" id="join">회원가입</button>

                <button type="button" id="back">돌아가기</button>
            </td>
        </tr>
      </table>
    </form>

    <script>
      var didIdCheck = false;

      let regexId = /^[a-z\d_]{8,}$/;
      let regexPw = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{8,}$/;
      let regexName = /^[가-힣]{2,5}$/;
      let regexPhone = /^01[\d]-?\d{4}-?\d{4}$/;
      let regexEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

      // 중복체크 버튼 눌렀을 때
      $("#id_check").on("click", function () {
        if ($("#id").val() == "") {
          alert("ID를 먼저 입력해주세요.");
          return;
        }
        
        window.open(
        		"/idcheck.members?id="+$("#id").val(),
        		"",
        		"width=300, height=200"
        	);
        
        didIdCheck = true;
        
      });

      // ID 사용 가능여부
      $("#id").on("keyup", function () {
        if ($("#id").val() == "") {
          $("#correct_id").html("");
          return false;
        }

        let result = regexId.test($("#id").val());
        if (result) {
          $("#correct_id").html("사용 가능한 ID입니다.");
          $("#correct_id").css("color", "green");
          $("#idcheck").attr("disabled", false); // 버튼 활성화
        } else {
          $("#correct_id").html("사용 불가한 ID입니다.");
          $("#correct_id").css("color", "red");
          $("#idcheck").attr("disabled", true); // 버튼 비활성화 (장애입히겠다)
        }
        
        didIdCheck = false;
      });

      // 패스워드 재확인
      $("#pw, #repw").on("keyup", function () {
        if ($("#pw").val() == "" || $("#repw").val() == "") {
          $("#correct_repw").html("");
          return false;
        }

        if ($("#pw").val() == $("#repw").val()) {
          $("#correct_repw").html("일치합니다.");
          $("#correct_repw").css("color", "green");
          idcheck.attr("disabled", false); // 버튼 활성화
        } else {
          $("#correct_repw").html("불일치합니다.");
          $("#correct_repw").css("color", "red");
          idcheck.attr("disabled", true); // 버튼 비활성화 (장애입히겠다)
        }
      });
      
      $("#back").on("click", function(){
    	  	location.href = "/index.jsp";
      });

      $("#join").on("click", function () {
        if (!regexId.test($("#id").val())) { 
          alert("ID를 먼저 입력해주세요.");
          return false;
          
        } else if (didIdCheck == false) {
        	  alert("ID 중복성 체크를 해주세요.");
        	  return false;
        	  
        } else if (!regexPw.test($("#pw").val())) {
          alert("PW를 먼저 입력해주세요.");
          return false;
          
        } else if (!regexPw.test($("#repw").val())) {
          alert("PW 재확인을 해주세요.");
          return false;
          
        } else if ($("#pw").val() != $("#repw").val()){
        	  alert("PW가 일치하지 않습니다.");
        	  return false;
        	  
        } else if (!regexName.test(name.val())) {
          alert("이름을 먼저 입력해주세요.");
          return false;
        }
      });
    </script>
  </body>
</html>
