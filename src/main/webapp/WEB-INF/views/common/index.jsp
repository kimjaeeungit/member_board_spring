<%@page import="com.kimjaeeun.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <jsp:include page="head.jsp"/>
</head>
<body>
    <div class="mainhome">
   <jsp:include page="header.jsp"/>
    <article>
    <section id="main">
        <div id="main1">
       		<div class="slider">
            	<img alt="daisies" src="${pageContext.request.contextPath}/resources/images/image7.jpg">
            	<img alt="plant" src="${pageContext.request.contextPath}/resources/images/image8.jpg">
            	<img alt="ucculents" src="${pageContext.request.contextPath}/resources/images/image9.jpg">
        	</div>
        </div>
        <div id="main2">
            <p style="font-size: 20px"><b>이벤트</b></p>
            <hr>
            <div id="main2img">
                <img src="resources/images/product1.PNG" width="80" height="80" alt="패드">
                <p style="font-size: 16px">아몬스 국산 명품패드 소형 300매</p>
                <p>47,400원</p>
            </div>
            <div id="main2img">
                <img src="resources/images/product3.PNG" width="80" height="80" alt="패드">
                <p style="font-size: 16px">아몬스 국산 시그니처 차콜 논슬립 패드 초대형 120매</p>
                <p>54,400원</p>
            </div>
        </div>
        <div id="main3">
             <p style="font-size: 20px"><b>자유게시판 최근글</b></p>
             <hr>
       		<ul class="row justify-content-left" style= "list-style:none;" >
      		 <c:forEach items="${list}" var="b">
         	  <li class="col-5 m-2">
          	 <a href="board/detail?bno=${b.bno}">
               <div>
                   <h4 class="text-truncate small"><c:out value="${b.title}" escapeXml="true"></c:out></h4>
                   <p class="text-truncate text-muted small">이상이 있음으로써 용감하고 굳세게 살수 있는</p>
               </div>
          	 </a>
          	 </li>
          	 </c:forEach>
           </ul>
        </div>
    </section>
    </article>
    <aside id="right">   
     <c:choose> 
     <c:when test="${empty member}"> 
        <form action="login">
        	   <input type="submit" value="로그인" id="loginbtn">
           </form>
       <p><a href="join" style="margin-left: 25px;">회원가입</a><a href="findid">ID/PW찾기</a></p>
        </c:when>
           <c:otherwise>
           <p><%=((Member)session.getAttribute("member")).getName() %> 님 환영합니다.</p>
            <p><a href="modify">정보수정</a> <a href="logout">로그아웃</a></p>
           </c:otherwise>
       </c:choose>
    </aside>
    <script>
  /* https://bxslider.com/options/ */
   $(function(){
	   setTimeout(function() {
		   $(".slider").bxSlider({
			   mode:'horizontal',
			   pager:false,
			   controls:false,
			   auto:true,
		   })
	   },100)
   });
   </script>
    <jsp:include page="footer.jsp"/>
       
</div>
</body>
</html>