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
		<h1>회원가입</h1>
		<form action="./update" method="post" enctype="multipart/form-data">

		  <div class="mb-3">
		    <label for="name" class="form-label">이름</label>
		    <input type="text" name="name" value="${user.name}" class="form-control" id="name">
		  </div>	
		  
		  <div class="mb-3">
			<label for="phone" class="form-label">전화번호</label>
			<input type="text" class="form-control" value="${user.phone}" name="phone" id="phone">
		   </div>		  	  	  
		  <div class="mb-3">
			<label for="email" class="form-label">Email</label>
			<input type="text" class="form-control" value="${user.email}" name="email" id="email">
		   </div>
		   
		   <div class="mb-3">
			<label for="profile" class="form-label">Profile</label>
			<input type="file" class="form-control" name="profile"  id="profile">
		   </div>
		   
		   <div>
		   	${user.userFileDTO.oldName} <span class="btn text-danger">X</span>
		   </div>
		   

		  <button type="submit" class="btn btn-primary">회원가입</button>
		</form>	
	</div>
</div>

<c:import url="/WEB-INF/views/templates/layout_footer.jsp"></c:import>
<c:import url="/WEB-INF/views/templates/boot_js.jsp"></c:import>
</body>
</html>