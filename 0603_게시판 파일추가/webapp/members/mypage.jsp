<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %> <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>내 정보</title>
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
    <form action="/edit.members" method="post">
      <div class="wrap">
        <div class="container">
          <div class="header">Member Details</div>
          <div class="body">
            <div class="row">
              <div class="txt">ID</div>
              <div class="content" id="id" contenteditable="false">
                ${member.id}
                <input
                  type="hidden"
                  name="id"
                  id="hidden_id"
                  value="${member.id}"
                />
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
              <div class="txt">Zipcode</div>
              <div class="content" id="zipcode" contenteditable="false">
                ${member.zipcode}
                <input
                  type="hidden"
                  name="zipcode"
                  id="hidden_zipcode"
                  value="${member.zipcode}"
                />
              </div>
            </div>
            <div class="row">
              <div class="txt">Address 1</div>
              <div class="content" id="add1" contenteditable="false">
                ${member.address1}
                <input
                  type="hidden"
                  name="add1"
                  id="hidden_add1"
                  value="${member.address1}"
                />
              </div>
            </div>
            <div class="row">
              <div class="txt">Address 2</div>
              <div class="content" id="add2" contenteditable="false">
                <span>${member.address2}</span>
                <input
                  type="hidden"
                  name="add2"
                  id="hidden_add2"
                  value="${member.address2}"
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
                <input
                  type="hidden"
                  name="join_date"
                  id="hidden_join_date"
                  value="${member.join_date}"
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
      let phone = $("#phone");
      let email = $("#email");
      let zipcode = $("#zipcode");
      let add1 = $("#add1");
      let add2 = $("#add2");
      let join_date = $("#join_date");

      // 수정버튼 눌렀을 시
      $("#edit").on("click", function () {
        $("#edit, #home").hide();
        $("#complete, #cancel").show();

        name.attr("contenteditable", true);
        phone.attr("contenteditable", true);
        email.attr("contenteditable", true);
        zipcode.attr("contenteditable", true);
        add1.attr("contenteditable", true);
        add2.attr("contenteditable", true);
      });

      // 홈 버튼 눌렀을 시
      $("#home").on("click", function () {
        location.href = "/index.jsp";
      });

      // 완료 버튼 눌렀을 시
      $("#complete").on("click", function () {
        $("#hidden_name").val($("#name").text().trim());
        $("#hidden_phone").val($("#phone").text().trim());
        $("#hidden_email").val($("#email").text().trim());
        $("#hidden_zipcode").val($("#zipcode").text().trim());
        $("#hidden_add1").val($("#add1").text().trim());
        $("#hidden_add2").val($("#add2").text().trim());
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
