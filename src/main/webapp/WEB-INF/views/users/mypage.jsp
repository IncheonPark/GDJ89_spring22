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
		<div class="alert alert-primary" role="alert">
  			${sessionScope.user.userName}
		</div>
		<div class="alert alert-primary" role="alert">
  			${user.userName}
		</div>
		<div class="alert alert-primary" role="alert">
  			${user.email}
		</div>
		
		<div class="alert alert-primary" role="alert">
			
  			<img alt="" src="/resources/images/profiles/${not empty user.userFileDTO.fileName ? user.userFileDTO.fileName:'default.jpg'}">
		</div>
		
		<div>
			<a href="./update" class="btn btn-danger">수정</a>
		</div>
		
	</div>
</div>

<c:import url="/WEB-INF/views/templates/layout_footer.jsp"></c:import>
<c:import url="/WEB-INF/views/templates/boot_js.jsp"></c:import>
</body>
</html>