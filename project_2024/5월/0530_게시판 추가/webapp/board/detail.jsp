<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>게시글 상세보기</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>

    <style>
      * {
        box-sizing: border-box;
      }
      /* div {
        border: 1px solid rgb(219, 219, 219);
      } */

      .wrap {
        border: none;
      }
      .container {
        width: 800px;
        margin: auto;
        display: flex;
        flex-direction: column;
        padding: 20px;
        border: 2px solid rgb(217, 217, 217);
        background-color: #f9f9f9;
      }

      .top {
        display: flex;
        justify-content: center;
        align-items: center;
        background-color: #a79c8e;
        color: white;
        font-weight: bold;
        padding: 10px;
      }

      .details {
        display: flex;
        flex-direction: column;
        padding: 10px;
      }

      .details .detail {
        display: flex;
        margin-bottom: 10px;
      }

      .details .detail .label {
        width: 100px;
        font-weight: bold;
      }

      .details .detail .value {
        flex: 1;
      }

      .contents {
        margin-top: 20px;
        padding: 10px;
        background-color: white;
        min-height: 200px;
        border: 1px solid #ccc;
      }

      .footer {
        display: flex;
        justify-content: flex-end;
        margin-top: 20px;
      }

      .footer #back {
        height: 30px;
        background-color: #eb9f9f;
        color: white;
        border: none;
        border-radius: 8px;
        cursor: pointer;
        margin-left: 8px;
      }

      .footer #delete,
      #edit {
        height: 30px;
        background-color: #ddc477;
        color: white;
        border: none;
        border-radius: 8px;
        cursor: pointer;
        margin-left: 6px;
      }

      .footer #complete,
      #cancel {
        height: 30px;
        background-color: #95a4e8;
        color: white;
        border: none;
        border-radius: 8px;
        cursor: pointer;
        margin-left: 6px;
      }
    </style>
  </head>
  <body>
    <form action="/edit.board" method="post">
      <div class="wrap">
        <div class="container">
          <div class="top">
            <label for="top">게시글 상세보기</label>
          </div>
          <div class="details" var="dto" items="${dto }">
            <div class="detail">
              <div class="label">글번호:</div>
              <div class="value">${dto.seq}</div>
            </div>
            <div class="detail">
              <div class="label">제목:</div>
              <div class="value" id="title">
                ${dto.title}
              </div>
            </div>
            <div class="detail">
              <div class="label">작성자:</div>
              <div class="value">${dto.writer}</div>
            </div>
            <div class="detail">
              <div class="label">작성일:</div>
              <div class="value">${dto.write_date}</div>
            </div>
            <div class="detail">
              <div class="label">조회수:</div>
              <div class="value">${dto.view_count}</div>
            </div>
          </div>
          <div class="contents" id="contents">
            ${dto.contents}
          </div>
          <div class="footer">
            <c:if test="${dto.writer eq loginID }">
              <button type="button" id="edit">수정</button>
              <button type="button" id="delete">삭제</button>
              <button type="submit" id="complete" style="display: none">
                완료
              </button>
              <button type="button" id="cancel" style="display: none">
                취소
              </button>
            </c:if>
            <button type="button" id="back">목록으로</button>
          </div>
        </div>
      </div>
      
      <input type="hidden" name="seq" value="${dto.seq}" />
      <input type="hidden" name="title" id="hidden_title" value="${dto.title}" />
      <input
        type="hidden"
        name="contents"
        id="hidden_contents"
        value="${dto.contents}"
      />
    </form>

    <script>
      $("#back").on("click", function () {
        location.href = "/list.board";
      });

      $("#delete").on("click", function () {
       	if(confirm("삭제하시겠습니까?")) {
         location.href = "/delete.board?seq=${dto.seq}";
       	}
      });

      // 수정버튼 눌렀을 때
      $("#edit").on("click", function () {
       	$("#edit, #delete").hide();
        $("#complete, #cancel").show();

        $("#title").attr("contenteditable", true);
        $("#title").focus();
        $("#contents").attr("contenteditable", true);
      });

      // 완료버튼 눌렀을 때
      $("#complete").on("click", function () {
        $("#hidden_title").val($("#title").text().trim());
        $("#hidden_contents").val($("#contents").text().trim());
      });


      // 취소버튼 눌렀을 때
      $("#cancel").on("click", function () {
        /* location.href = "/detail.board?seq="+${dto.seq}; */
        location.reload();
      });
    </script>
  </body>
</html>
