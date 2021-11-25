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
    <title>회원탈퇴 화면</title>
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
	 .links{
        text-align: center;
        margin-bottom: 10px;
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
		 <p style="font-size: 20px; margin-bottom: 10px;"><b>비밀번호 입력</b></p>
      <form class="form-signin" method="POST">
  		 <p class="text2"> ${findid2}</p>
        <input type="password" name="pwd" id="pwd" class="form-control" placeholder="비밀번호" required autofocus><BR>
        <input id="btn-Yes" class="btn btn-lg btn-primary btn-block" type="submit" value="탈퇴" /> 
      </form>
		</div>
	<h3>${param.msg}</h3>
   <jsp:include page="../common/footer.jsp" />
   </div>
  </body>
  <script type="text/javascript">
		
  		
  
  		$("#name").focusout(function(){
  			
	     if($('#name').val() == ""){
	   		$('#check').text('이름을 입력해주세요.');
	   	  	$('#check').css('color', 'red');
	   
	     }else{
	    	 $('#check').hide();
	     }
	     });
	     
  		$("#email").focusout(function(){
	     if($('#email').val() == ""){
	   		$('#check').text('이메일을 입력해주세요');
	   	  	$('#check').css('color', 'red');
	     }else{
	    	 $('#check').hide();
	     }
	     });
  
  </script>
</html>