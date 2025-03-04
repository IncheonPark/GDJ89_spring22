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
		<h1>${kind} Detail Page</h1>
		
		<table class="table table-dark ">
			<thead>
				<tr >
					<th>TITLE</th>
					<th>WRITER</th>
					<th>DATE</th>
					<th>HIT</th>
				</tr>
			</thead>
			
			<tbody>
				<tr>
					<td>${dto.boardTitle}</td>
					<td>${dto.userName}</td>
					<td>${dto.boardDate}</td>
					<td>${dto.boardHit}</td>
				</tr>

		
		</table>
		
		
		<div class="alert alert-primary" role="alert" style="min-height: 60vh">
		  ${dto.boardContents}
		</div>
	
		<div>
			<c:forEach items="${dto.boardFileDTOs}" var="f">
				<a href="/resources/images/${kind}/${f.fileName}">${f.oldName}</a>
			</c:forEach>
		</div>
	

	
		
		<div>
			<a href="./update?boardNum=${dto.boardNum}" class="btn btn-outline-success">수정</a>
			<a href="./delete?boardNum=${dto.boardNum}" class="btn btn-outline-danger">삭제</a>
			<a href="./reply?boardNum=${dto.boardNum}" class="btn btn-outline-primary">답글</a>
		</div>		
		
	</div>
</div>

<c:import url="/WEB-INF/views/templates/layout_footer.jsp"></c:import>
<c:import url="/WEB-INF/views/templates/boot_js.jsp"></c:import>
</body>
</html>