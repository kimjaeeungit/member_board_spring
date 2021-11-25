<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
     <title>자유게시판</title>
    <jsp:include page="../common/head.jsp" />

</head>
<body>
    <div class="listpage">
        <jsp:include page="../common/header.jsp"/>
        <article>
    <section>
   
    <table class="table"  id="notice">
    	<p style="font-size: 20px; text-align: center; margin-top: 40px; " ><b>자유게시판</b></p>
        <thead>
            <tr>
                <th>연번</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
            </tr>
        </thead>
        <tbody>
           <c:forEach items="${list}" var="b">
            <tr>
                <td>${b.bno}</td>
                <td><a href="detail?bno=${b.bno}">${b.title}</a><span class = "text-muted small">[${b.replyCnt}]</span></td>
                <td>${b.id==null ? '탈퇴회원':b.id}</td>
                <td><fmt:formatDate value="${b.regDate}" pattern="yy/MM/dd"/></td>
            </tr>
           </c:forEach>
           <tr>
      <td colspan="4">
         <ul class="pagination justify-content-end">
           <li class="page-item ${page.prev ? '' : 'disabled'}">
              <a class="page-link" href="list?pageNum=${page.startPage-1}&amount=${page.cri.amount}">Previous</a>
           </li>
           
           <c:forEach begin="${page.startPage}" end="${page.endPage}" var="p">
           <li class="page-item ${p == page.cri.pageNum ? 'active' : ''}">
           <a class="page-link" href="list?pageNum=${p}&amount=${page.cri.amount}">${p}</a>
           </li>
           </c:forEach>
           <li class="page-item ${page.next ? '' : 'disabled'}">
           <a class="page-link" href="list?pageNum=${page.endPage+1}&amount=${page.cri.amount}">Next</a></li>
 	 	 </ul>
        <%--  ${page} --%>
      </td>
      </tr>
        </tbody>
         <c:if test="${not empty member}">
        <tr>
      <td colspan = "4"><a href="write" class="btn btn-primary float-right">글쓰기</a></td>
   </tr>
    </c:if>
    </table>
    </section>
    </article>
    <jsp:include page="../common/footer.jsp"/>
    </div>
</body>
</html>