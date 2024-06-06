<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>정보수정</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <style>
      * {
        box-sizing: border-box;
      }
      /* div {
        border: 1px solid black;
      } */

      /*.container div {
        border: 1px solid #b1b1b1;
      } */

      .container {
        width: 400px;
        height: 500px;
        margin: auto;
        display: flex;
        flex-direction: column;
        border: 1px solid black;
        border-radius: 5px;
        padding: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      }

      .container .header {
        flex: 1;
        display: flex;
        justify-content: center;
        align-items: center;
        font-size: x-large;
        font-weight: bolder;
      }
      .container .body {
        flex: 6.5;
        display: flex;
        flex-direction: column;
      }

      .container .body .row {
        flex: 1;
        display: flex;
      }

      .container .body .row .txt {
        flex: 2;
        display: flex;
        align-items: center;
        font-weight: bold;
        padding-left: 5px;
      }

      .container .body .row .content {
        flex: 5;
        display: flex;
        align-items: center;
        padding-left: 5px;
      }

      .container .footer {
        flex: 0.8;
        display: flex;
        justify-content: center;
        align-items: center;
      }

      .container .footer #edit,
      #home {
        margin: 5px;
        background-color: #d9dbf1;
        color: black;
        border-radius: 5px;
        border: none;
        height: 30px;
        cursor: pointer;
      }
      .container .footer #complete,
      #cancel {
        margin: 5px;
        background-color: #fce4a3;
        color: black;
        border-radius: 5px;
        border: none;
        height: 30px;
        cursor: pointer;
      }
    </style>
  </head>
  <body>
    <form action="/update.mypage" method="post">
      <div class="wrap">
        <div class="container">
          <div class="header">Member Details</div>
          <div class="body">
            <div class="row">
              <div class="txt">ID</div>
              <div class="content" id="id" name="id" contenteditable="false">
                ${member.id}
              </div>
            </div>
            <div class="row">
              <div class="txt">Name</div>
              <div class="content" id="name" contenteditable="false">
                ${member.name}
                <input
                  type="hidden"
                  name="name"
                  id="hidden_name"
                  value="${member.name}"
                />
              </div>
            </div>
            <div class="row">
              <div class="txt">NickName</div>
              <div class="content" id="nickname" contenteditable="false">
                ${member.nickname}
                <input
                  type="hidden"
                  name="nickname"
                  id="hidden_nickname"
                  value="${member.nickname}"
                />
              </div>
            </div>
            <div class="row">
              <div class="txt">Phone</div>
              <div class="content" id="phone" contenteditable="false">
                ${member.phone}
                <input
                  type="hidden"
                  name="phone"
                  id="hidden_phone"
                  value="${member.phone}"
                />
              </div>
            </div>
            <div class="row">
              <div class="txt">Email</div>
              <div class="content" id="email" contenteditable="false">
                ${member.email}
                <input
                  type="hidden"
                  name="email"
                  id="hidden_email"
                  value="${member.email}"
                />
              </div>
            </div>
            <div class="row">
              <div class="txt">Gender</div>
              <div class="content" id="gender" contenteditable="false">
                ${member.gender}
              </div>
            </div>
            <div class="row">
              <div class="txt">Birth</div>
              <div class="content" id="birth" contenteditable="false">
                ${member.birth}
              </div>
            </div>
            <div class="row">
              <div class="txt">Grade</div>
              <div class="content" id="grade" contenteditable="false">
                <span>${member.grade}</span>
              </div>
            </div>
            <div class="row">
              <div class="txt">Avatar</div>
              <div class="content" id="avatar" contenteditable="false">
                ${member.avatar}
                <input
                  type="hidden"
                  name="avatar"
                  id="hidden_avatar"
                  value="${member.avatar}"
                />
              </div>
            </div>
            <div class="row">
              <div class="txt">Join Date</div>
              <div class="content" id="join_date" contenteditable="false">
                <fmt:formatDate
                  value="${member.join_date}"
                  pattern="yyyy.MM.dd"
                />
              </div>
            </div>
          </div>
          <div class="footer">
            <button type="button" id="edit">수정하기</button>
            <button type="button" id="home">홈으로</button>
            <button type="submit" id="complete" style="display: none">
              완료
            </button>
            <button type="button" id="cancel" style="display: none">
              취소
            </button>
          </div>
        </div>
      </div>
    </form>

    <script>
      // 사용변수
      let id = $("#id");
      let name = $("#name");
      let nickname = $("#nickname");
      let phone = $("#phone");
      let email = $("#email");
      let avatar = $("#avatar");

      // 수정버튼 눌렀을 시
      $("#edit").on("click", function () {
        $("#edit, #home").hide();
        $("#complete, #cancel").show();

        name.attr("contenteditable", true);
        nickname.attr("contenteditable", true);
        phone.attr("contenteditable", true);
        email.attr("contenteditable", true);
        avatar.attr("contenteditable", true);
        
      });

      // 홈 버튼 눌렀을 시
      $("#home").on("click", function () {
        location.href = "/index.jsp";
      });

      // 완료 버튼 눌렀을 시
      $("#complete").on("click", function () {
        $("#hidden_name").val($("#name").text().trim());
        $("#hidden_nickname").val($("#nickname").text().trim());
        $("#hidden_phone").val($("#phone").text().trim());
        $("#hidden_email").val($("#email").text().trim());
        $("#hidden_avatar").val($("#avatar").text().trim());
      });

      // 취소 버튼 눌렀을 시
      $("#cancel").on("click", function () {
        location.href = "/mypage.members";
      });

      // enter 입력하지 못하게. 여기에 유효성검사해도됨
      $("td").on("keydown", function (e) {
        if (e.key == "Enter") {
          return false;
        }
      });
    </script>
  </body>
</html>
