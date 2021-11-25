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
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<td><input type="file" name="file1"></td>
			</tr>
			<tr>
				<td><input type="file" name="file2"></td>
			</tr>
			<tr>
				<td><input type="file" name="file3"></td>
			</tr>
			<tr>
				<td><textarea name="content"></textarea></td>
			</tr>
			<tr>
			<button>작성</button>
			</tr>
    </table>
    </form>
    <a href="notice.html"><input type="button" value="글쓰기" name="글쓰기" id="write"></a>
    <a href="list"><input type="button" value="취소" name="취소" id="cancle"></a>
    <jsp:include page="../common/footer.jsp"/>
    </div>
</body>
</html>