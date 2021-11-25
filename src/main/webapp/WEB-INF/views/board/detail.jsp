<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세페이지</title>
<jsp:include page="../common/head.jsp" />
<style>
#btn{
	margin-top:10px;
	margin-bottom:10px;
	margin-right:10px;
	}
</style>
</head>
<body>
	<div class="mainhome">
		<jsp:include page="../common/header.jsp" />
		<input class="btn btn-primary float-right disabled" id="btn"  type="button" value="목록" onclick="changeView()" >
		<!-- 글쓴이 아이디랑 로그인아이디 같아야 보이는거 -->
		<c:if test="${member.id eq board.id}">
			<div class="col-10 mx-auto">
		 		<input class="btn btn-primary float-right disabled" id="btn" type="button" value="수정" onclick="doAction(0)">
				<input class="btn btn-primary float-right disabled" id="btn" type="button" value="삭제" onclick="doAction(1)">	
			</div>
		</c:if>
		<article>
			<section id="freelist">
				<!-- <table class="detail"> -->
				<table class="table col-10 mx-auto" style="table-layout: fixed">
					<tr>
						<th>제목</th>
						<td colspan="3"><c:out value="${board.title}"/></td>
					</tr>
					<tr>
					<th>작성자</th>
						<td><c:out value="${board.id==null ? '탈퇴회원':board.id}"/></td>
					<th>작성일</th>
						<td><c:out value="${board.regDate}"/></td>
					</tr>
					<tr>
					<th>첨부파일</th>
						<td colspan="3">
				        <c:forEach items="${board.attachs}" var="attach">
				        <p><a href="${pageContext.request.contextPath}/download?filename=${attach.path}/${attach.uuid}">${attach.origin}</a></p>
				        </c:forEach>
				     </td>
					</tr>
					<tr>
					<th>내용</th>
						<td colspan="3"><c:out value="${board.content}"/></td>
					</tr>
				</table>
				
				<!-- 로그인해야만 보이는거 -->
				<c:if test="${not empty member}">
					<div class="clearfix col-10 mx-auto">
						<div class="form-group">
							<p><c:out value="${member.name}"/></p>
							<form id="frmReplyWrite">
								<input type="text" class="form-control"
									placeholder="댓글 제목을 입력하세요" name="title" id="title">
								<textarea class="form-control" placeholder="댓글을 입력하세요"
									name="content" id="content"></textarea>
								<input type="hidden" name="bno" value="${board.bno}"><input
									type="hidden" name="id" value="${member.id}">
								<button class="btn btn-primary float-right disabled"
									id="btnReplyWrite">등록</button>
							</form>
						</div>
					</div>
				</c:if>
				<div class="col-10 mx-auto reply-wrapper"></div>
			</section>
		</article>
		<jsp:include page="../common/footer.jsp" />
	</div>
<script>
		function doAction(value)
		{
			if(value==0) 
				location.href="update?bno=${board.bno}";//수정
			else if(value==1)//삭제
				location.href="remove?bno=${board.bno}";
		}
		
	        function changeView()
	        {
	            location.href=location.href="list";
	        }
	 
	
		var cp = "${pageContext.request.contextPath}";
		var bno = '${param.bno}';
		$(function() {
			//댓글 목록 불러오기
			showList();
			function showList() {
				var url = cp + "/reply/list?bno=" + bno;
				console.log(url);

				$
						.getJSON(url)
						.done(
								function(data) {
									console.log(data);

									var str = "";
									for ( var i in data) {
										str += '<div class="card my-3 border-secondary" data-rno="'+data[i].rno+'">'
										str += '	<div class="card-header bg-dark text-light">'
												+ (data[i].title==null?'탈퇴회원':'') + '</div>'
										str += '	<div class="card-body">'
												+ data[i].content + '</div>'
										str += '</div>'
									}
									$(".reply-wrapper").html(str);

								})
			}

			//이벤트 위임 댓글 상세 이벤트
			$(".reply-wrapper").on(
					"click",
					".card",
					function() {
						var url = cp + "/reply?rno=" + $(this).data("rno");
						$.getJSON(url).done(
								function(data) {
									console.log(data);
									console.log(data.title);
									console.log(data.content);
									$("#myModal").data("rno", data.rno).find(
											".modal-title").text(data.title)
											.end().find(".modal-body").text(
													data.content).end().modal(
													"show");
								});
						/*          alert($(this).data("rno")); */

					})

			$("#btnRm").click(function() {
				alert($(this).closest(".modal").data("rno"));
				//위에 this == <button type="button" class="btn btn-danger" data-dismiss="modal" id="btnRemove">삭제</button>
				var rno = $(this).closest(".modal").data("rno");
				var url = cp + "/reply?rno=" + rno;

				//$.ajax(url,{})
				$.ajax(url, {
					method : "delete",
					success : function() {
						//성공적으로 종료
						showList();
						$("#myModal").modal("hide");
					}
				});
			});

			$("#title, #content").keyup(function() {
				var titleLen = $("#title").val().trim().length;
				var contentLen = $("#content").val().trim().length;

				if (titleLen && contentLen) {
					$("#btnReplyWrite").removeClass("disabled");
				} else {
					$("#btnReplyWrite").addClass("disabled");
				}
			});

			$("#frmReplyWrite").submit(function() {
				event.preventDefault();
				if ($("#btnReplyWrite").is(".disabled"))
					return;

				var reply = {};
				reply.title = $(this.title).val();
				reply.content = $(this.content).val();
				reply.id = $(this.id).val();
				reply.bno = $(this.bno).val();

				var data = JSON.stringify(reply);

				var frm = this; //밑에 ajax가 디스
				var url = cp + "/reply"
				$.ajax(url, {
					method : "post",
					data : {
						"jsonData" : data
					},
					success : function(data) {
						showList();
						frm.reset();
						$("#btnReplyWrite").addClass("disabled");
					}
				})
			});
		/* 	//글삭제
			$("#goList").click(function() {
				$.ajax( {
					method : "delete",
					success : function() {
						//성공적으로 종료
						alert("삭제되었습니다.");
					}
				});
			}); */

		});
</script>
<!-- The Modal -->
<div class="modal" id="myModal">
	<div class="modal-dialog">
		<div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">Modal Heading</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>

			<!-- Modal body -->
			<div class="modal-body">Modal body..</div>

			<!-- Modal footer -->
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal"
					id="btnRm">삭제</button>
			</div>

		</div>
	</div>
</div>
</body>
</html>