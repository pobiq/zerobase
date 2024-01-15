<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>와이파이 정보 구하기</title>
	<script>
		
	</script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu.css">
	<style>
		
		#bookToolbar {
			margin-bottom : 5px;
		}
		
		#list {
			width : 100%;
			border : 1px solid #E3DBDE;
			border-spacing: 0px;
		}
		
		#list tr:nth-child(even) {
			background-color : #F2F2F2;
		}
		
		#list > thead > tr > th {
			padding : 5px 5px;
			background-color : #00AC6F;
			color : white;
		}
		
		#list > thead > tr > th:not(:last-child) {
			border-right : 1px solid white;
		}
		
		#list > tbody > tr > td {
			padding : 10px 0px;
			border : 1px solid #E3DBDE;
			text-align : left;
			padding-left : 5px;
		}
		
		#list > tbody > tr td:last-child {
			text-align : center;
		}
		
	</style>
</head>
<body>
	<h1>북마크 그룹</h1>
	<jsp:include page="${pageContext.request.contextPath}/common/menus.jsp"/>
	
	<form action="/Servlet" method="GET">
		<div id="bookToolbar">
			<input type="hidden" name="command" value="bookMarkGroupAddForm.do">
			<input type="submit" value="북마크 그룹 이름 추가">
		</div>
	</form>
	
	<table id="list">
		<colgroup>
            <col width="10%;">
            <col width="20%;">
            <col width="10%;">
            <col width="20%;">
            <col width="20%;">
            <col width="20%;">
        </colgroup>
		<thead>
			<tr>
				<th>ID</th>
				<th>북마크 이름</th>
				<th>순서</th>
				<th>등록일자</th>
				<th>수정일자</th>
				<th>비고</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty bookMarkGroupList}">
					<c:forEach var="item" items="${bookMarkGroupList}">
						<tr>
							<td>${item.id}</td>
							<td>${item.name}</td>
							<td>${item.preference}</td>
							<td>${item.regist_date}</td>
							<td>${item.update_date}</td>
							<td>
								<a href="/Servlet?command=bookMarkGroupEditForm.do&id=${item.id}">수정</a>
								<a href="/Servlet?command=bookMarkGroupDeleteForm.do&id=${item.id}">삭제</a>
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="6" style="text-align:center">
							정보가 존재하지 않습니다.
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
			
		</tbody>
		
	</table>
</body>
</html>