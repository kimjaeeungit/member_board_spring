<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <meta charset="utf-8">
    <jsp:include page="../common/head.jsp" />
    <!-- Bootstrap CSS -->
    <title>아이디 찾기</title>
    <style>
    input{ margin-top:15px;
    }
    .card {
        margin: 0 auto; /* Added */
        float: none; /* Added */
        margin-bottom: 10px; /* Added */
        margin-top: 20px;
	}

      #btn-Yes{
        background-color: rgb(49 52 82);
        border: none;
        margin-top:15px;
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
            <p style="font-size: 20px; margin-bottom: 10px;"><b>비밀번호 찾기</b></p>
                <form action="mail" id="form1" class="form-signin"  method="post" > 
                    <div class="input-box"> 
                        <input class="form-control"  id="email" type="text" name="email" placeholder="이메일을 입력하세요" required>
                    </div> 
                    <div><input type="hidden" readonly="readonly" name="code_check"
					id="code_check" value="<%=getRandom()%>" /></div>
                   <button id="btn-Yes" class="btn btn-lg btn-primary btn-block" type="submit">임시 비밀번호 발급</button>
                </form>
                </div>
        <div class="links">
            <a href="findid">아이디 찾기</a> | <a href="login">로그인</a> | <a href="join">회원가입</a>

        </div>
       	</div>
       	<jsp:include page="../common/footer.jsp"/>
     </div>
</body>
  <%! public int getRandom(){
	int random = 0;
	random = (int)Math.floor((Math.random()*(99999-10000+1)))+10000;
	return random;
} %>
</html>