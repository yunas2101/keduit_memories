<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %> <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>

    <style>
      * {
        box-sizing: border-box;
      }
      div {
        border: 1px solid rgb(219, 219, 219);
      }

	  .wrap {border:none;}
      .container {
        /* border: 2px solid black; */
        width: 800px;
        height: 600px;
        margin: auto;
        display: flex;
        flex-direction: column;
      }

      /* top */
      .container .top {
        flex: 1;
        display: flex;
        justify-content: center;
        align-items: center;
        background-color: #a79c8e;
        color: white;
        font-weight: bold;
      }

      /* header */
      .container .header {
        flex: 1;
        display: flex;
        background-color: #efe9d9;
      }
      .container .header .seq {
        flex: 1;
        display: flex;
        justify-content: center;
        align-items: center;
      }
      .container .header .title {
        flex: 9;
        display: flex;
        justify-content: center;
        align-items: center;
      }
      .container .header .writer {
        flex: 2;
        display: flex;
        justify-content: center;
        align-items: center;
      }
      .container .header .write_date {
        flex: 2;
        display: flex;
        justify-content: center;
        align-items: center;
      }
      .container .header .view_count {
        flex: 1;
        display: flex;
        justify-content: center;
        align-items: center;
      }

      /* body */
      .container .body {
        flex: 12;
        padding: 2px;
      }
      .container .body .content_list {
        width: 100%;
        height: 100%;
      }
      .container .body .content_list .list {
        width: 100%;
        height: 40px;
        display: flex;
      }

      .list #seq {
        flex: 1;
        display: flex;
        align-items: center;
        justify-content: center;
      }
      .list #title {
        flex: 9;
        display: flex;
        align-items: center;
        padding-left: 5px;
      }
      .list #writer {
        flex: 2;
        display: flex;
        align-items: center;
        justify-content: center;
      }
      .list #write_date {
        flex: 2;
        display: flex;
        align-items: center;
        justify-content: center;
      }
      .list #view_count {
        flex: 1;
        display: flex;
        align-items: center;
        justify-content: center;
      }

      /* pagination */
      .container .pagination {
        flex: 1;
      }

      /* footer */
      .container .footer {
        flex: 1;
        display: flex;
        justify-content: flex-end;
        align-items: center;
        height: 50px;
      }
      .container .footer #write_btn, #home_btn {
        height: 30px;
        margin-right: 5px;
        background-color: #eb9f9f;
        color: white;
        border: none;
        border-radius: 8px;
        cursor: pointer;
      }
    </style>
  </head>
  <body>
    <div class="wrap">
      <div class="container">
        <div class="top">
          <label for="top">자유 게시판</label>
        </div>
        <div class="header">
          <div class="seq">
            <label for="seq"></label>
          </div>
          <div class="title">
            <label for="title">제목</label>
          </div>
          <div class="writer">
            <label for="writer">작성자</label>
          </div>
          <div class="write_date">
            <label for="write_date">날짜</label>
          </div>
          <div class="view_count">
            <label for="view_count">조회</label>
          </div>
        </div>

        <div class="body">
          <div class="content_list">
            <c:forEach var="dto" items="${list}">
              <div class="list">
                <div id="seq">${dto.seq}</div>
                <div id="title">
                  <a href="/detail.board?seq=${dto.seq }">${dto.title}</a>
                </div>
                <div id="writer">${dto.writer}</div>
                <div id="write_date">
                  <fmt:formatDate
                    value="${dto.write_date}"
                    pattern="yyyy.MM.dd"
                  />
                </div>
                <div id="view_count">${dto.view_count}</div>
              </div>
            </c:forEach>
          </div>
        </div>

        <div class="pagination"></div>
        <div class="footer">
          <button id="write_btn">작성하기</button>
          <button id="home_btn">홈으로</button>
        </div>
      </div>
    </div>

    <script>
      $("#write_btn").on("click", function () {
        location.href = "/board/writeform.jsp";
      });
      $("#home_btn").on("click", function () {
        location.href = "/index.jsp";
      });
    </script>
  </body>
</html>
