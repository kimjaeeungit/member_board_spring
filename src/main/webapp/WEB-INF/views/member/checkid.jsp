<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <meta charset="utf-8">
    <jsp:include page="../common/head.jsp" />
    <!-- Bootstrap CSS -->
    <title>아이디 찾기</title>
    <style>
    .card {
        margin: 0 auto; /* Added */
        float: none; /* Added */
        margin-bottom: 10px; /* Added */
        margin-top: 20px;
	}

    #btn-Yes{
        background-color: rgb(49 52 82);
        border: none;
    }
	
	.form-signin .form-control {
  		position: relative;
  		height: auto;
  		-webkit-box-sizing: border-box;
     	-moz-box-sizing: border-box;
        	 box-sizing: border-box;
  		padding: 10px;
  		font-size: 16px;
	}
    .card-title{
        margin-left: 30px;
    }
	
    a{ 
    	color: rgb(49 52 82); text-decoration: none; 
    }
    .text2{
    	color : blue;
    }
    </style>
  </head>

  <body>
  <div class="listpage">
  <jsp:include page="../common/header.jsp" />

	<div class="card align-middle" style="width:25rem;">
		<div class="card-body">
		 <p style="font-size: 20px; margin-bottom: 10px;"><b>아이디확인</b></p>
      	 <p style="font-size: 20px; margin-bottom: 10px;"><b>아이디 :${id} </b></p>
		<a href="index.html" id="btn-Yes" class="btn btn-lg btn-primary btn-block">메 인 으 로</a>
		</div>
	</div>
   <jsp:include page="../common/footer.jsp" />
   </div>
  </body>

</html>