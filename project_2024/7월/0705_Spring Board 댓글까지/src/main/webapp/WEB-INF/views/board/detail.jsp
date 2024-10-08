<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>상세보기</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <style>
      * {
        box-sizing: border-box;
      }
      /* div {
        border: 1px solid rgb(219, 219, 219);
      } */

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

      /* reply */
      .wrap .reply_area {
        width: 800px;
        margin: auto;
        padding-top: 5px;
      }

      .wrap .reply_area .reply_inputbox {
        width: 100%;
        height: 70px;
        display: flex;
        border: 2px solid rgb(217, 217, 217);
      }

      .wrap .reply_area .reply_inputbox .input {
        flex: 7;
        padding: 5px;
      }

      .wrap .reply_area .reply_inputbox .input #input_reply {
        width: 100%;
        height: 100%;
        border: 1px solid #d9d9d9;
        border-radius: 5px;
      }

      .wrap .reply_area .reply_inputbox .btn {
        flex: 1;
        padding: 5px;
        display: flex;
        justify-content: center;
        align-items: center;
      }

      .wrap .reply_area .reply_inputbox .btn button {
        width: 60%;
        height: 80%;
        border: none;
        border-radius: 8px;
        background-color: #eb9f9f;
        color: white;
        cursor: pointer;
      }

      /* 댓글 출력 */
      .wrap .reply_area .reply_listbox {
        width: 100%;
        padding-top: 20px;
        padding-bottom: 10px;
        display: flex;
        justify-content: center;
      }

      .reply_listbox .reply_list {
        width: 87%;
        height: 100px;
        display: flex;
        flex-direction: column;
      }

      .reply_listbox .reply_list .reply_top {
        flex: 1;
        font-weight: bold;
      }

      .reply_listbox .reply_list .reply_middle {
        flex: 1;
        margin-left: 5px;
      }

      .reply_listbox .reply_list .reply_bottom {
        flex: 1;
        display: flex;
      }

      .reply_listbox .reply_list .reply_bottom .reply_date {
        flex: 7;
        font-size: smaller;
        color: #5b5b5b;
      }

      .reply_listbox .reply_list .reply_bottom .reply_btns {
        flex: 2;
        display: flex;
        justify-content: center;
        align-items: center;
      }

      .reply_bottom .reply_btns button {
        height: 20px;
        margin: 5px;
        background-color: #ddc477;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: small;
      }

    </style>
  </head>
  <body>
    <div class="wrap">
      <form action="/board/updateBoard" method="post">
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
              <div class="value" id="title">${dto.title}</div>
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
          <div class="contents" id="contents">${dto.contents}</div>
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

        <input type="hidden" name="seq" value="${dto.seq}" />
        <input type="hidden" name="title" class="hidden_title" />
        <input type="hidden" name="contents" class="hidden_contents" />
      </form>
      
      <div class="reply_area">
        <form action="/reply/insert" method="post">
          <div class="reply_inputbox">
            <div class="input">
              <input type="text" placeholder="댓글을 남겨보세요." name="contents"
                id="input_reply" />
            </div>
            <div class="btn">
              <button type="submit" id="reply_btn">등록</button>
            </div>
          </div>
          <input type="hidden" name="parent_seq" value="${dto.seq}" />
        </form>

        <c:forEach var="reply" items="${reply}">
           <div class="reply_listbox">
             <div class="reply_list">
               <div class="reply_top">${reply.writer}</div>
               <div class="reply_middle reply_contents" >${reply.contents}</div>
               <div class="reply_bottom">
                 <div class="reply_date">${reply.write_date}</div>
                 <c:if test="${reply.writer eq loginID }">
                   <div class="reply_btns">
                     <button type="button" class="reply_edit">수정</button>
                     <button type="button" class="reply_del"
                       data-seq="${reply.seq}">삭제</button>
                     <button type="submit" class="reply_ok"  data-seq="${reply.seq}" style="display: none">완료</button>
                     <button type="button" class="reply_cancel" data-seq="${reply.seq}"
                       style="display: none">취소</button>
                   </div>
                 </c:if>
               </div>
             </div>
           </div>
           <input type="hidden" name="hidden_reply_contents"
             class="hidden_reply_contents" value="${reply.contents }">
            <input type="hidden"
             name="hidden_reply_parentseq" value="${reply.parent_seq }">
        </c:forEach>
		  </div>
      
    </div>

    <script>
      $("#back").on("click", function () {
        location.href = "/board/list";
      });

      /* 수정 버튼 */
      $("#edit").on("click", function () {
        $("#edit, #delete").hide();
        $("#complete, #cancel").show();

        $("#title").attr("contenteditable", true);
        $("#title").focus();
        $("#contents").attr("contenteditable", true);
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
      
      /* reply */
      /* 댓글 삭제 */
      $(".reply_del").on("click", function(){
        let parent=$(this).parents(".reply_listbox");
    	  $.ajax({
          url: "/reply/delete",
          type:"get",
          data: {seq: $(this).data("seq")}
        }).done(function(resp){
            parent.remove();
            conso
            alert("삭제");
        })
      })

      /* 댓글 수정 */
      $(".reply_edit").on("click", function(){
        let parent = $(this).parents(".reply_listbox");
        parent.find((".reply_ok, .reply_cancel")).show();
        parent.find((".reply_edit, .reply_del")).hide();
        
        parent.find((".reply_contents")).attr("contenteditable", true);

      })
      
      /* 완료 */
      $(".reply_ok").on("click",function(){
        let parent = $(this).parents(".reply_listbox");
    
        $.ajax({
          url:"/reply/update",
          data:{
            seq:$(this).data("seq"),
            contents:parent.find(".reply_contents").html().trim()
          }

        }).done(function(){
          parent.find((".reply_ok, .reply_cancel")).hide();
          parent.find((".reply_edit, .reply_del")).show();    
          parent.find(".reply_contents").attr("contenteditable", false);
        })
      })

      $(".reply_cancel").on("click",function(){
        let parent = $(this).parents(".reply_listbox");

        $.ajax({
          url: "/reply/cancel",
          type:"get",
          data: {seq: $(this).data("seq")}
        }).done(function(resp){
          parent.find((".reply_ok, .reply_cancel")).hide();
          parent.find((".reply_edit, .reply_del")).show();    
          parent.find(".reply_contents").attr("contenteditable", false);
          parent.find(".reply_contents").html(resp);
        })
      })

    </script>
  </body>
</html>
