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
		<h1>Product Detail Page</h1>
		
		<h3>${dto.productName}</h3>
		<h3>${dto.productRate}</h3>
		
		<div class="mb-3">
			<form id="frm" action="/test">
				<input type="hidden" name="productNum" value="${dto.productNum}">
				<button type="button" id="up">수정</button>
				<button type="button" id="del">삭제</button>
			</form>
			<button type="button" id="addCart" data-product-num="${dto.productNum}">장바구니</button>

		</div>

		<div class="mb-3">
			<div class="mb-3">
				<label for="commentsContents" class="form-label">댓글</label>
				<textarea class="form-control" id="commentsContents" rows="3"></textarea>
				<button type="button" class="btn btn-outline-primary mt-3" id="addComments"  data-product-num="${dto.productNum}">등록</button>
			</div>
		</div>

		<div class="mb-3" id="commentsListResult">

		</div>

	</div>
</div>



<c:import url="/WEB-INF/views/templates/layout_footer.jsp"></c:import>
<c:import url="/WEB-INF/views/templates/boot_js.jsp"></c:import>
<script src="../resources/js/products/detail.js"></script>
</body>
</html>