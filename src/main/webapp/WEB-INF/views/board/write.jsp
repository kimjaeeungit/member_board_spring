<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
     <title>글쓰기</title>
    <jsp:include page="../common/head.jsp" />
</head>
<body>
    <div class="mainhome">
          <jsp:include page="../common/header.jsp"/>
    	<form method="post" enctype="multipart/form-data">
   		<table class="write">
     		<tr>
				<td class="p-0"><input class="form-control" type="text" name="title" style="width: 600px; border:0" placeholder="제목을 입력하세요.">
				<input class="form-control" type="text" name="category" style="width: 600px; border:0"  value="1" hidden="hidden">
				</td>
				
			</tr>
			<tr>
				<td class="p-0"><input class="form-control p-1"  type="file" name="file1" style=" border:0;"></td>
			</tr>
			<tr>
				<td class="p-0"><input class="form-control p-1"  type="file" name="file2" style=" border:0;"></td>
			</tr>
			<tr>
				<td class="p-0"><input class="form-control p-1"  type="file" name="file3" style=" border:0;"></td>
			</tr>
			<tr>
				<td class="p-0"><textarea class="p-3" name="content" style="width: 600px; height :300px;border:0;" placeholder="내용을 입력하세요."></textarea></td>
			</tr>
    </table>
    <button id="write">작성</button>&nbsp;
  </form>
  	<a href="list"><input type="button" value="취소" name="취소" id="cancle"></a>
    <jsp:include page="../common/footer.jsp"/>
    </div>
</body>
</html>