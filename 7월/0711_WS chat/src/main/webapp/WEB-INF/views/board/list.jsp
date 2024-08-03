<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>게시글 목록</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/resources/css/boardList.css"
    />
  </head>
  <body>
    <div class="wrap">
      <div class="container">
        <div class="top">
          <h1>자유 게시판</h1>
        </div>

        <fieldset>
          <legend>검색</legend>
          <form action="/board/search" method="post">
            <input type="text" name="writer" placeholder="작성자" />
            <input type="text" name="title" placeholder="제목" />
            <button>검색</button>
          </form>
        </fieldset>

        <div class="header">
          <div class="seq">번호</div>
          <div class="title">제목</div>
          <div class="writer">작성자</div>
          <div class="write_date">날짜</div>
          <div class="view_count">조회</div>
        </div>
        <div class="body">
          <div class="content_list">
            <c:forEach var="dto" items="${list}">
              <div class="list">
                <div class="seq">${dto.seq}</div>
                <div class="title">
                  <a href="/board/detail?seq=${dto.seq }">${dto.title}</a>
                </div>
                <div class="writer">${dto.writer}</div>
                <div class="write_date">
                  <fmt:formatDate
                    value="${dto.write_date}"
                    pattern="yyyy.MM.dd"
                  />
                </div>
                <div class="view_count">${dto.view_count}</div>
              </div>
            </c:forEach>
          </div>
        </div>
        <span class="pagination" id="navi"></span>
        <div class="footer">
          <button id="write_btn">작성하기</button>
          <button id="home_btn">홈으로</button>
        </div>
      </div>
    </div>

    <script>
      $("#write_btn").on("click", function() {
          location.href = "/board/writeForm";
      });

      $("#home_btn").on("click", function() {
          location.href = "/";
      });

      let navi = $("#navi");
      let cpage = ${cpage};
      let record_total_count = ${record_total_count};
      let record_count_per_page = ${record_count_per_page};
      let navi_count_per_page = ${navi_count_per_page};
      let page_total_count;

      if (record_total_count % record_count_per_page == 0) {
          page_total_count = (Math.floor(record_total_count / record_count_per_page));
      } else {
          page_total_count = Math.floor(record_total_count / record_count_per_page) + 1;
      }

      let startNavi = Math.floor((cpage - 1) / navi_count_per_page) * navi_count_per_page + 1;
      let endNavi;
      if ((startNavi + navi_count_per_page - 1) < page_total_count) {
          endNavi = (startNavi + navi_count_per_page - 1)
      } else {
          endNavi = page_total_count
      }

      let needNext = true;
      let needPrev = true;
      if (startNavi == 1) needPrev = false;
      if (endNavi == page_total_count) needNext = false;

      let needPrevMax = true;
      let needNextMax = true;

      if (startNavi == 1) needPrevMax = false;
      if (endNavi == page_total_count) needNextMax = false;

      if (needPrevMax) {
          let pagelink = $("<a href='/board/list?cpage=1'>«</a>");
          pagelink.css("text-decoration", "none");
          navi.append(pagelink);
      }

      if (needPrev) {
          let pagelink = $("<a href='/board/list?cpage=" + (startNavi - 1) + "'>⏴</a>");
          pagelink.css("text-decoration", "none");
          navi.append(pagelink);
      }

      for (let i = startNavi; i <= endNavi; i++) {
          let pagelink = $("<a href='/board/list?cpage=" + i + "'>" + i + "</a>");
          pagelink.css("text-decoration", "none");
          navi.append(pagelink);
      }

      if (needNext) {
          let pagelink = $("<a href='/board/list?cpage=" + (endNavi + 1) + "'>⏵</a>");
          pagelink.css("text-decoration", "none");
          navi.append(pagelink);
      }

      if (needNextMax) {
          let pagelink = $("<a href='/board/list?cpage=" + page_total_count + "'>»</a>");
          pagelink.css("text-decoration", "none")
          navi.append(pagelink);
      }
    </script>
  </body>
</html>
