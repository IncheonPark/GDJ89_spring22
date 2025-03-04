<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="/WEB-INF/views/templates/boot_css.jsp"></c:import>

</head>
<body>
<c:import url="/WEB-INF/views/templates/layout_header.jsp"></c:import>

<div class="continer-fluid my-5">
	<div class="row col-md-8 offset-md-2">
		<!-- contents 내용 작성 -->
		<h1>Notice Add</h1>
		<form action="" method="post" enctype="multipart/form-data">
		  <input type="hidden" name="boardNum" value="${dto.boardNum}">
		  		  
		  <div class="mb-3">
		    <label for="boardTitle" class="form-label">Title</label>
		    <input type="text" value="${dto.boardTitle}" name="boardTitle" placeholder="글제목" class="form-control" id="boardTitle">
		  </div>
		  
		  <div class="mb-3">
			<label for="boardContents" class="form-label">Contents</label>
			<textarea class="form-control" name="boardContents" id="boardContents" rows="10">${dto.boardContents}</textarea>
		   </div>
		   		  	  	  
		   <div id="files" class="mb-3">


			<button class="btn btn-success" type="button" id="add_file">파일추가</button>
		   </div>

		  <button type="submit" class="btn btn-primary">글작성</button>
		</form>	
	</div>
</div>
<script src="/resources/js/files/fileManger.js"></script>
<c:import url="/WEB-INF/views/templates/layout_footer.jsp"></c:import>
<c:import url="/WEB-INF/views/templates/boot_js.jsp"></c:import>
</body>
</html>