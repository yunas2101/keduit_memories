<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

.footer #delete, #edit {
	height: 30px;
	background-color: #ddc477;
	color: white;
	border: none;
	border-radius: 8px;
	cursor: pointer;
	margin-left: 6px;
}

.footer #complete, #cancel {
	height: 30px;
	background-color: #95a4e8;
	color: white;
	border: none;
	border-radius: 8px;
	cursor: pointer;
	margin-left: 6px;
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

p {
	text-align: center;
	font-weight: bold;
}
</style>

<script>
   /* $(function(){
    		replyList(${dto.seq});
    		
	});
    		
    function replyList(seq){
    		console.log("a");
    		
    	
    	
    }
    */
    </script>

</head>
<body>
	<div class="wrap">
		<form action="/edit.board" method="post">
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
					<div class="detail">
						<div class="label">첨부파일:</div>
						<c:forEach var="i" items="${files }">
							<div class="value">
								<a
									href="/download.file?sysname=${i.sysname }&oriname=${i.oriname}">${i.oriname }</a>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="contents" id="contents">${dto.contents}</div>
				<div class="footer">
					<c:if test="${dto.writer eq loginID }">
						<button type="button" id="edit">수정</button>
						<button type="button" id="delete">삭제</button>
						<button type="submit" id="complete" style="display: none">
							완료</button>
						<button type="button" id="cancel" style="display: none">
							취소</button>
					</c:if>
					<button type="button" id="back">목록으로</button>
				</div>
			</div>

			<input type="hidden" name="seq" value="${dto.seq}" /> <input
				type="hidden" name="title" id="hidden_title" value="${dto.title}" />
			<input type="hidden" name="contents" id="hidden_contents"
				value="${dto.contents}" />
		</form>

		<div class="reply_area">
			<form action="/insert.reply" method="post">
				<div class="reply_inputbox">
					<div class="input">
						<input type="text" placeholder="댓글을 남겨보세요." name="reply_contents"
							id="input_reply" />
					</div>
					<div class="btn">
						<button type="button" id="reply_btn">등록</button>
					</div>
				</div>
				<input type="hidden" name="seq" value="${dto.seq}" />
			</form>

			<!-- 댓글 들어가는 곳 -->
			<div id="replyList"></div>

			<!-- 댓글이 없다면 "댓글주세요" 보이게 -->
			<c:choose>
				<c:when test="${reply.size() eq 0 }">
					<p>댓글이 필요합니다.</p>
				</c:when>
				<c:otherwise>
					<c:forEach var="reply" items="${reply}">

						<form action="/edit.reply" method="post">
							<div class="reply_listbox">
								<div class="reply_list">
									<div class="reply_top">${reply.writer}</div>
									<div class="reply_middle" id="reply_contents">${reply.contents}</div>
									<div class="reply_bottom">
										<div class="reply_date">${reply.write_date}</div>
										<c:if test="${reply.writer eq loginID }">
											<div class="reply_btns">
												<button type="button" class="reply_edit">수정</button>
												<button type="button" class="reply_del"
													data-seq="${reply.seq}">삭제</button>
												<button type="submit" class="reply_ok" style="display: none">완료</button>
												<button type="button" class="reply_cancel"
													style="display: none">취소</button>
											</div>
										</c:if>
									</div>
								</div>
							</div>
							<input type="hidden" name="hidden_reply_contents"
								class="hidden_reply_contents" value="${reply.contents }">
							<input type="hidden" name="hidden_reply_seq"
								value="${reply.seq }"> <input type="hidden"
								name="hidden_reply_parentseq" value="${reply.parent_seq }">
						</form>

					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
	</div>


						

	
	<script>
    // < 게시글 > 
    
    
    
    
    
    
      $("#back").on("click", function () {
        location.href = "/list.board";
      });

      // 게시글 삭제버튼 눌렀을 때
      $("#delete").on("click", function () {
        if (confirm("삭제하시겠습니까?")) {
          location.href = "/delete.board?seq=${dto.seq}";
        }
      });

      // 게시글 수정버튼 눌렀을 때
      $("#edit").on("click", function () {
        $("#edit, #delete").hide();
        $("#complete, #cancel").show();

        $("#title").attr("contenteditable", true);
        $("#title").focus();
        $("#contents").attr("contenteditable", true);
       
      });

      // 게시글 완료버튼 눌렀을 때
      $("#complete").on("click", function () {
        $("#hidden_title").val($("#title").text().trim());
        $("#hidden_contents").val($("#contents").text().trim());
      });

      // 게시글 취소버튼 눌렀을 때
      $("#cancel").on("click", function () {
        /* location.href = "/detail.board?seq="+${dto.seq}; */
        location.reload();
      });
      
      
      // < 댓글 >
      
      //등록버튼
      $("#reply_btn").on("click",function(){
    	  $.ajax({
  			url: "/insert.reply",
  			data:{
  				seq:"${dto.seq}",
				reply_contents: $("#input_reply").val()
				
  			},
  			dataType: "json"
  		}).done(function(resp){
  			var form = $("<form>").attr({
    action: "/edit.reply",
    method: "post"
});

// reply_listbox div 엘리먼트 생성
var replyListbox = $("<div>").addClass("reply_listbox");

// reply_list div 엘리먼트 생성
var replyList = $("<div>").addClass("reply_list");

// reply_top div 엘리먼트 생성 및 내용 설정
var replyTop = $("<div>").addClass("reply_top").text(resp.writer);

// reply_middle div 엘리먼트 생성 및 내용 설정
var replyMiddle = $("<div>").addClass("reply_middle").attr("id", "reply_contents").text(resp.contents);

// reply_bottom div 엘리먼트 생성
var replyBottom = $("<div>").addClass("reply_bottom");

// reply_date div 엘리먼트 생성 및 내용 설정
var replyDate = $("<div>").addClass("reply_date").text(resp.write_date);

// reply_bottom에 reply_date 추가
replyBottom.append(replyDate);

if("${loginID}"==resp.writer){
	// reply_btns div 엘리먼트 생성
var replyBtns = $("<div>").addClass("reply_btns");

// 수정 버튼 생성
var editBtn = $("<button>").attr({
    type: "button",
    class: "reply_edit"
}).text("수정");

// 삭제 버튼 생성
var delBtn = $("<button>").attr({
    type: "button",
    class: "reply_del",
    "data-seq": resp.seq
}).text("삭제");

// 완료 버튼 생성 (초기에는 숨겨진 상태)
var okBtn = $("<button>").attr({
    type: "submit",
    class: "reply_ok",
    style: "display: none"
}).text("완료");

// 취소 버튼 생성 (초기에는 숨겨진 상태)
var cancelBtn = $("<button>").attr({
    type: "button",
    class: "reply_cancel",
    style: "display: none"
}).text("취소");

// reply_btns에 버튼들 추가
replyBtns.append(editBtn, delBtn, okBtn, cancelBtn);

// reply_bottom에 reply_btns 추가
replyBottom.append(replyBtns);
}


// reply_list에 reply_top, reply_middle, reply_bottom 추가
replyList.append(replyTop, replyMiddle, replyBottom);

// reply_listbox에 reply_list 추가
replyListbox.append(replyList);

// hidden_reply_contents input 엘리먼트 생성
var hiddenReplyContents = $("<input>").attr({
    type: "hidden",
    name: "hidden_reply_contents",
    class: "hidden_reply_contents",
    value: resp.contents
});

// hidden_reply_seq input 엘리먼트 생성
var hiddenReplySeq = $("<input>").attr({
    type: "hidden",
    name: "hidden_reply_seq",
    value: resp.seq
});

// hidden_reply_parentseq input 엘리먼트 생성
var hiddenReplyParentSeq = $("<input>").attr({
    type: "hidden",
    name: "hidden_reply_parentseq",
    value: resp.parent_seq
});

// form에 hidden input 엘리먼트들 추가
form.append(replyListbox, hiddenReplyContents, hiddenReplySeq, hiddenReplyParentSeq);

// body에 form 추가

       

		
		
		
        /*
		 let replyElement = $(replyHTML);
        
        // Event binding for dynamically added elements
        replyElement.find(".reply_del").on("click", function() {
            if (confirm("삭제하시겠습니까?")) {
                location.href = `/delete.reply?seq=${dto.seq}&reply_seq=${resp.seq}`;
            }
        });

        replyElement.find(".reply_edit").on("click", function() {
            $(this).hide(); // 수정
            $(this).next().hide(); // 삭제
            $(this).siblings(".reply_ok").show(); // 완료
            $(this).siblings(".reply_cancel").show(); // 취소

            $(this).closest(".reply_list").find(".reply_middle").attr("contenteditable", true).focus();
        });

        replyElement.find(".reply_ok").on("click", function() {
            $(this).closest("form").find(".hidden_reply_contents").val($(this).closest(".reply_list").find(".reply_middle").text().trim());
        });

        replyElement.find(".reply_cancel").on("click", function() {
            location.reload();
        });

*/




        $("#replyList").prepend(form);
  				
  	
	
				
				
  			
  		});
      })
      
      
      // 삭제버튼 눌렀을 때
		$(".reply_del").on("click" , function(){
		  if (confirm("삭제하시겠습니까?")) {
			location.href = "/delete.reply?seq=${dto.seq}&reply_seq="+this.dataset.seq; 
		  }
		});

      // 수정버튼 눌렀을 때
      $(".reply_edit").on("click", function(){

	    	$(this).hide(); // 수정
	    	$(this).next().hide(); // 삭제
	    	$(this).siblings(".reply_ok").show(); // 완료
	    	$(this).siblings(".reply_cancel").show(); // 취소

	    	$(this).closest(".reply_list").find(".reply_middle").attr("contenteditable", true);
	    	$(this).closest(".reply_list").find(".reply_middle").focus();
	
        // 완료버튼 눌렀을 때
         $(this).siblings(".reply_ok").on("click", function(){
        	   $(this).closest("form").find(".hidden_reply_contents").val($(this).closest(".reply_list").find(".reply_middle").text().trim());
        });	
      });


      // 취소버튼 눌렀을 때
      $(".reply_cancel").on("click", function () {
        location.reload();
      });
    </script>
</body>
</html>
