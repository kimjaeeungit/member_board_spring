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
            <p style="font-size: 20px; margin-bottom: 10px;"><b>회원가입</b></p>
                <form  class="form-signin"  method="post" onsubmit="return checkAll()"> 
                    <label for="id">아이디</label> 
                    <div class="input-box"> 
                        <input class="form-control"  id="id" type="text" name="id" placeholder="아이디" required> 
                        <p class="msg text-danger"></p>   
                    </div> 
                    <label for="pwd">비밀번호</label> 
                    <div class="input-box"> 
                        <input class="form-control"  id="pwd" type="password" name="pwd" placeholder="비밀번호" required>
                        <p class="msg text-danger"></p>
                    </div> 
                    <label for="pwd">비밀번호 확인</label> 
                    <div class="input-box"> 
                       <input class="form-control"  id="pwdCk" type="password" name="pwdCk" placeholder="비밀번호확인" required>
                        <p class="msg text-danger"></p>
                    </div> 
                    <label for="email">이메일</label> 
                    <div class="input-box"> 
                        <input class="form-control"  id="email" type="text" name="email" placeholder="이메일" required>
                        <p class="msg text-danger"></p>
                    </div> 
                    <label for="name">이름</label> 
                    <div class="input-box"> 
                        <input class="form-control"  id="name" type="text" name="name" placeholder="이름을 입력하세요" required> 
                        <p class="msg text-danger"></p>   
                    </div> 
                   <button class="btn btn-lg btn-primary btn-block" type="submit" id="btnJoin">회원가입</button>
                </form>
                	</div>
                </div>
	
 <script>
        
	//아이디 중복체크
	$(function(){
		$("#id").keyup(function(){
			//input창에 입력된 id가져옴
			var id = $("#id").val();
			var idRegExp = /^[a-zA-z0-9]{4,12}$/;
			if (!idRegExp.test(id)) {
				$("#id").next().text("아이디는 영문 대소문자와 숫자 4~12자리로 입력해야합니다.");
				 $("#id").val()="";
		            $("#id").focus();
			}
			if(id){
				$.ajax("idValid?id="+id, {
					//success가 0(false)또는 1(true)
					success : function(data){
						if(data/1){
							$("#id").next().text("사용할수 있는 아이디 입니다.");
						}
						else{
							$("#id").next().text("이미 가입된 회원입니다.");
						}
					}
				})
			}
			event.preventDefault();
		})
	});
	
	//비밀번호 동일한지 체크
	 $(function(){
		$("#pwdCk,#pwd").keyup(function(){
			var pwd = $("#pwd").val();
			var pwdCk = $("#pwdCk").val();
				if(pwd != pwdCk){
					$("#pwdCk").next().text("불일치");
				}
				else{
					$("#pwdCk").next().text("일치");
				}
			event.preventDefault();
		})
	}); 
	
	
	//이메일 체크
	$(function(){
		$("#email").keyup(function(){
			var email = $("#email").val();
			 var emailRegExp = /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/;
			if(!emailRegExp.test(email)){
				$("#email").next().text("사용할 수 없는 이메일입니다.");
				$("#email").val()="";
		        $("#email").focus();
			}
			else{
				$("#email").next().text("사용가능한 이메일입니다.");
			}
			event.preventDefault();
		})
	});
	
	//이름 체크
	$(function(){
		$("#name").keyup(function(){
			var name = $("#name").val();
			var nameRegExp = /^[가-힣]{2,4}$/;
			if(!nameRegExp.test(name)){
				$("#name").next().text("이름이 올바르지 않습니다.");
				$("#name").val()="";
		        $("#name").focus();
			}
			else{
				$("#name").next().text("");
			}
			event.preventDefault();
		})
	});
	
	
</script>
<jsp:include page="../common/footer.jsp"/>
</div>
</body>
</html>