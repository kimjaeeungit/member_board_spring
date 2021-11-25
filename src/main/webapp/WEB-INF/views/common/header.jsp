<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<header class="pt-4">
	<div class="mt-4 clearfix">
		<a class="float-left mb-3" href="${pageContext.request.contextPath}/common/index.jsp"><img src = "${pageContext.request.contextPath}/resources/images/logo.PNG" id="mainimg" alt="메인사진"  width="200px" height="70px" >
        </a>
        <h4 class="float-right">강아지용품 전문몰 도그팡</h4>
    </div> 
     <nav >
        <ul>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/notice/list" >공지사항</a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/board/list">자유게시판</a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/gallery/list" >갤러리</a></li>
        </ul>
    </nav>
</header>