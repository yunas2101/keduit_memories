<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>상세보기</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/resources/css/boardDetail.css"
    />
  </head>
  <body>
    <div class="wrap">
      <form action="/board/updateBoard" method="post">
        <div class="container">
          <div><span>게시글 상세보기</span></div>
          <div>
            <div class="detail">
              <div class="label"><span>글 번호</span></div>
              <div class="value">${dto.seq}</div>
            </div>
            <div class="detail">
              <div class="label"><span>제목</span></div>
              <div class="value" id="title">${dto.title}</div>
            </div>
            <div class="detail">
              <div class="label"><span>작성자</span></div>
              <div class="value">${dto.writer}</div>
            </div>
            <div class="detail">
              <div class="label"><span>작성일</span></div>
              <div class="value">${dto.write_date}</div>
            </div>
            <div class="detail">
              <div class="label"><span>조회수</span></div>
              <div class="value">${dto.view_count}</div>
            </div>
          </div>
          <div id="contents">${dto.contents}</div>
          <div>
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

        <input type="hidden" name="seq" value="${dto.seq}" />
        <input type="hidden" name="title" class="hidden_title" />
        <input type="hidden" name="contents" class="hidden_contents" />
      </form>

      <div class="reply_container">
        <form action="/reply/insert" method="post">
          <div class="reply_input">
            <div>
              <input
                type="text"
                placeholder="댓글을 입력해주세요."
                name="contents"
              />
            </div>
            <div><button type="submit">등록</button></div>
          </div>
          <input type="hidden" name="parent_seq" value="${dto.seq}" />
        </form>

        <c:forEach var="reply" items="${reply}">
          <div class="reply_list">
            <div>
              <div>${reply.writer}</div>
              <div class="reply_contents">${reply.contents}</div>
              <div>${reply.write_date}</div>
            </div>
            <div>
              <c:if test="${reply.writer eq loginID }">
                <button type="button" class="edit">수정</button>
                <button type="button" class="del" data-seq="${reply.seq}">
                  삭제
                </button>
                <button
                  type="button"
                  class="ok"
                  data-seq="${reply.seq}"
                  style="display: none"
                >
                  완료
                </button>
                <button
                  type="button"
                  class="cancel"
                  data-seq="${reply.seq}"
                  style="display: none"
                >
                  취소
                </button>
              </c:if>
            </div>
          </div>
        </c:forEach>
      </div>
    </div>

    <script>
      //==========[ 게시글 ]==========
      /* 목록으로 */
      $("#back").on("click", function () {
        location.href = "/board/list";
      });

      /* 수정 버튼 */
      $("#edit").on("click", function () {
        $("#edit, #delete").hide();
        $("#complete, #cancel").show();

        $("#title, #contents").attr("contenteditable", true);
        $("#title").focus();
      });

      /* 완료 버튼 */
      $("#complete").on("click", function () {
        $("#edit, #delete").show();
        $("#complete, #cancel").hide();

        $(".hidden_title").val($("#title").text().trim());
        $(".hidden_contents").val($("#contents").text().trim());
      });

      /* 취소 버튼 */
      $("#cancel").on("click", function () {
       location.reload();
      });

      /* 삭제 버튼 */
      $("#delete").on("click", function () {
       if (confirm("정말 삭제하시겠습니까?")) {
        location.href="/board/deleteBoard?seq="+${dto.seq};
       }
      });

      //==========[ 댓글 ]==========
      /* 댓글 삭제 */
      $(".del").on("click", function(){
        let parent = $(this).parents(".reply_list");
        $.ajax({
          url: "/reply/delete",
          type: "post",
          data : {seq : $(this).data("seq")}
        }).done(function(resp){
          parent.remove();
        })
      })

      /* 댓글 수정 */
      $(".edit").on("click", function(){
        let parent = $(this).parents(".reply_list");

        parent.find((".ok, .cancel")).show();
        parent.find((".edit, .del")).hide();
        parent.find((".reply_contents")).attr("contenteditable", true);
        parent.find((".reply_contents")).focus();
      })

      /* 댓글 완료 */
      $(".ok").on("click", function(){
        let parent = $(this).parents(".reply_list");

        $.ajax({
          url : "/reply/update",
          data: {
            seq: $(this).data("seq"),
            contents: parent.find(".reply_contents").html().trim()
          }
        }).done(function(resp){
          parent.find((".ok, .cancel")).hide();
          parent.find((".edit, .del")).show();
          parent.find((".reply_contents")).attr("contenteditable", false);
        })
      })

      /* 댓글 취소 */
      $(".cancel").on("click", function(){
        // location.reload();

        let parent = $(this).parents(".reply_list");

        $.ajax({
          url: "/reply/cancel",
          type: "post",
          data: {seq: $(this).data("seq")}
        }).done(function(resp){
          parent.find((".ok, .cancel")).hide();
          parent.find((".edit, .del")).show();
          parent.find((".reply_contents")).attr("contenteditable", false);
          parent.find(".reply_contents").html(resp);
        })
      })
    </script>
  </body>
</html>
