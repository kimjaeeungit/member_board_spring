<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
     <title>글수정</title>
    <jsp:include page="../common/head.jsp" />
     <style type="text/css">
        #title{
            height : 16;
            font-family :'돋움';
            font-size : 12;
            text-align :center;
        }
    </style>


</head>
<body>
    <div class="mainhome">
          <jsp:include page="../common/header.jsp"/>
          <br>
    <b><font size="6" color="gray">글 수정</font></b>
    <br>
    <form method="post" enctype="multipart/form-data">
      <input type="hidden" name="board_num" value="${board.bno}"/>
    <input type="hidden" name="existing_file" value="${board.attachs}"/>

    <table class="write">
      		<tr>
            	<td id="title">작성자</td>
           		<td>${board.id}</td>
      		</tr>
      		 <tr>
            <td id="title">
                제 목
            </td>
				<td><input name="title" type="text" size="70" maxlength="100" 
                    value="${board.title}"/>
            	</td>        
       		 </tr>
       		 <tr>
            <td id="title">
                내 용
            </td>
            <td>
                <textarea name="content" cols="72" rows="20">
                    ${board.content}
                </textarea>            
            </td>        
        </tr>
         <tr>
                <td id="title">
                    기존 파일
                </td>
                <td>
                    <c:forEach items="${board.attachs}" var="attach">
								<p>${attach.origin}</p>
							</c:forEach>
							 </td>    
            </tr>
            <tr>
                <td id="title">
                    첨부파일
                </td>
                <td>
                    <input type="file" name="file1"/>
                </td>    
            </tr>
			<tr align="center" valign="middle">
            <td colspan="5">
                <input type="reset" value="작성취소" >
                <input type="submit" value="수정" >
                <input type="button" value="목록" onclick="changeView()" >            
            </td>
        </tr>
    </table>
    </form>
      <script type="text/javascript">
        function changeView()
        {
            location.href=location.href="list";
        }
    </script>

    <jsp:include page="../common/footer.jsp"/>
    </div>
</body>
</html>