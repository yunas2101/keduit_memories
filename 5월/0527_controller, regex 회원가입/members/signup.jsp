<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>SignUp Form</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

    <style>
      * {
        box-sizing: border-box;
      }
      /* div {
      border: 1px solid black;
    } */

      #warn {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: flex-start;
        color: red;
        margin: auto;
        width: 700px;
      }

      .container {
        width: 600px;
        height: 800px;
        margin: auto;
        display: flex;
        flex-direction: column;
      }
      /* header */
      .container .header {
        flex: 1;
        display: flex;
        justify-content: center;
        align-items: center;
        font-size: x-large;
        font-weight: bold;
      }

      /* body */
      .container .body {
        flex: 10;
        display: flex;
        flex-direction: column;
      }

      .container .body .row {
        flex: 1;
      }

      .container .body .id_box,
      .pw_box,
      .repw_box,
      .name_box,
      .phone_box,
      .email_box,
      .post_box,
      .add1_box,
      .add2_box {
        display: flex;
      }
      .container .body .text {
        flex: 2.5;
        display: flex;
        align-items: center;
      }

      .container .body .input_id,
      .input_pw,
      .input_repw,
      .input_name,
      .input_phone,
      .input_email,
      .input_post,
      .input_add1,
      .input_add2 {
        flex: 9;
      }

      input {
        width: 100%;
        height: 70%;
        margin-top: 8px;
        border-radius: 10px;
        border: 2px solid #908f8f;
      }
      .input_id input {
        width: 80%;
        height: 70%;
        margin-top: 8px;
      }
      .input_id #idcheck {
        height: 45px;
        background-color: dodgerblue;
        color: white;
        border: none;
        border-radius: 5px;
        margin: 5px;
      }

      .input_post input {
        width: 80%;
        height: 70%;
        margin-top: 8px;
      }

      .input_post #post_btn {
        width: 70px;
        height: 45px;
        background-color: dodgerblue;
        color: white;
        border: none;
        border-radius: 5px;
        margin: 5px;
      }

      /* footer */
      .container .footer {
        flex: 1;
        display: flex;
        align-items: center;
        justify-content: center;
      }

      .container .footer button {
        margin: 10px;
        height: 40px;
        background-color: #158815;
        color: white;
        border: none;
        border-radius: 5px;
      }
    </style>
  </head>

  <body>
    <div class="wrqp">
      <div id="warn">
        <div>1> ID / 패스워드 / 이름은 필수항목</div>
        <div>2> ID는 8자 이상의 알파벳 소문자, 숫자, _로만 구성</div>
        <div>
          3> 패스워드는 알파벳 대문자, 소문자, 숫자를 각 하나 이상은
          포함해야하며, 최소 8자 이상
        </div>
        <div>4> 이름은 한글로 2 ~ 5자만 가능</div>
        <div>
          5> 전화번호 및 이메일은 입력하지 않으면 무시하되, 입력이 되었다면
          형식에 맞아야 함
        </div>
        <div>6> 주소에는 요효성 검사 없음</div>
      </div>

      <form action="/signup.members" id="joinform">
        <div class="container">
          <div class="header">회원가입</div>

          <div class="body">
            <div class="row id_box">
              <div class="text">아이디</div>
              <div class="input_id">
                <input
                  type="text"
                  id="id"
                  name="id"
                  placeholder="8자 이상의 알파벳 소문자, 숫자, _로만 구성"
                />
                <button id="idcheck">중복확인</button>
              </div>
            </div>
            <div class="correct_id"></div>

            <div class="row pw_box">
              <div class="text">패스워드</div>
              <div class="input_pw">
                <input
                  type="text"
                  id="pw"
                  name="pw"
                  placeholder="알파벳 대문자, 소문자, 숫자 각 하나 이상 포함, 최소 8자 이상"
                />
              </div>
            </div>

            <div class="row repw_box">
              <div class="text">패스워드 확인</div>
              <div class="input_repw">
                <input
                  type="text"
                  id="repw"
                  name="repw"
                  placeholder="패스워드 재입력"
                />
              </div>
            </div>
            <div class="correct_repw"></div>

            <div class="row name_box">
              <div class="text">이름</div>
              <div class="input_name">
                <input type="text" id="name" name="name" placeholder="이름" />
              </div>
            </div>
            <div class="correct_name"></div>

            <div class="row phone_box">
              <div class="text">전화번호</div>
              <div class="input_phone">
                <input
                  type="text"
                  id="phone"
                  name="phone"
                  placeholder="전화번호 ('-' 생략)"
                />
              </div>
            </div>
            <div class="correct_phone"></div>

            <div class="row email_box">
              <div class="text">이메일</div>
              <div class="input_email">
                <input
                  type="text"
                  id="email"
                  name="email"
                  placeholder="이메일"
                />
              </div>
            </div>
            <div class="correct_email"></div>

            <div class="row post_box">
              <div class="text">우편번호</div>
              <div class="input_post">
                <input
                  type="text"
                  id="post"
                  name="post"
                  placeholder="우편번호"
                />
                <input
                  type="button"
                  id="post_btn"
                  onclick="postBtn()"
                  value="검색"
                />
              </div>
            </div>
            <div class="row add1_box">
              <div class="text">주소 1</div>
              <div class="input_add1">
                <input type="text" id="add1" name="add1" placeholder="주소1" />
              </div>
            </div>
            <div class="row add2_box">
              <div class="text">주소 2</div>
              <div class="input_add2">
                <input type="text" id="add1" name="add2" placeholder="주소2" />
              </div>
            </div>
          </div>
          <div class="footer">
            <form action="/input.members">
              <button id="join" name="join">회원가입</button>
            </form>
            <button id="back">돌아가기</button>
          </div>
        </div>
      </form>
    </div>

    <script>
      var didIdCheck = false;

      let id = $("#id");
      let idcheck = $("#idcheck");
      let pw = $("#pw");
      let repw = $("#repw");
      let name = $("#name");
      let phone = $("#phone");
      let email = $("#email");

      // 중복체크
      id.on("keyup", function () {
        let regex = /^[a-z\d_]{8,}$/;
        let result = regex.test(id.val());
        let didIdCheck = 0;

        if (result) {
          $(".correct_id").html("사용 가능한 ID입니다.");
          $(".correct_id").css("color", "green");
          didIdCheck = 1;
          idcheck.attr("disabled", false);
        } else {
          $(".correct_id").html("사용 불가한 ID입니다.");
          $(".correct_id").css("color", "red");
          didIdCheck = 0;
          idcheck.attr("disabled", true);
        }
      });
      idcheck.on("click", function () {
        if (id.val() == "") {
          alert("ID를 먼저 입력해주세요.");
          return;
        }
        window.open(
          "/idcheck.members?id=" + id.val(),
          "",
          "width=300, height=200"
        ); // 팝업창을 통해 request 쏘기
      });
      // 패스워드 재확인
      pw.on("keyup", function (e) {
        let regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{8,}$/;

        let result = regex.test(pw.val());
        console.log(result);
        console.log(pw.val());

        if (result) {
          repw.on("keyup", function (e) {
            console.log(repw.val());
            if (repw.val() == pw.val()) {
              $(".correct_repw").html("일치합니다.");
              $(".correct_repw").css("color", "green");
            } else {
              $(".correct_repw").html("불일치합니다.");
              $(".correct_repw").css("color", "red");
            }
          });
        }
      });

      // 이름
      name.on("keyup", function (e) {
        let regex = /^[가-힣]{2,5}$/;
        let result = regex.test(name.val());

        if (result) {
          $(".correct_name").html("확인");
          $(".correct_name").css("color", "green");
        } else {
          $(".correct_name").html("불가");
          $(".correct_name").css("color", "red");
        }
      });

      // 전화번호 작성
      phone.on("keyup", function (e) {
        let regex = /^01[\d]-?\d{4}-?\d{4}$/;
        let result = regex.test(phone.val());

        if (result) {
          $(".correct_phone").html("확인");
          $(".correct_phone").css("color", "green");
        } else {
          $(".correct_phone").html("불가");
          $(".correct_phone").css("color", "red");
        }
      });

      // 이메일
      email.on("keyup", function (e) {
        let regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        let result = regex.test(email.val());

        if (result) {
          $(".correct_email").html("확인");
          $(".correct_email").css("color", "green");
        } else {
          $(".correct_email").html("불가");
          $(".correct_email").css("color", "red");
        }
      });

      function postBtn() {
        new daum.Postcode({
          oncomplete: function (data) {
            console.log(data);
            $("#post").val(data.zonecode);
            $("#add1").val(data.roadAddress);
          },
        }).open();
      }

      $("#back").on("click", function () {
        location.href = "/index.jsp";
      });

      $("#joinform").on("submit", function () {
        let idRegex = /^[a-z\d_]{8,}$/;
        let pwRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{8,}$/;
        let nameRegex = /^[가-힣]{2,5}$/;
        if (!idRegex.test(id.val())) {
          alert("ID를 먼저 입력해주세요.");
          return false;
        } else if (didIdCheck == false) {
          alert("ID 중복성 체크를해주세요.");
          return false;
        } else if (!pwRegex.test(pw.val())) {
          alert("PW를 먼저 입력해주세요.");
          return false;
        } else if (!pwRegex.test(repw.val())) {
          alert("PW를 먼저 입력해주세요.");
          return false;
        } else if (!nameRegex.test(name.val())) {
          alert("이름을 먼저 입력해주세요.");
          return false;
        }
      });
    </script>
  </body>
</html>
