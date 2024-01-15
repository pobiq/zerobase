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
		
	</style>
</head>
<body>
	<h1>위치 히스토리 목록</h1>
	<jsp:include page="${pageContext.request.contextPath}/common/menus.jsp"/>
	
	<table id="list">
		<colgroup>
            <col width="10%;">
            <col width="25%;">
            <col width="25%;">
            <col width="40%;">
        </colgroup>
		<thead>
			<tr>
				<th>ID</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>조회일자</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty locationList}">
					<c:forEach var="item" items="${locationList}">
						<tr>
							<td>${item.id}</td>
							<td>${item.lat}</td>
							<td>${item.lnt}</td>
							<td>${item.regist_time}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="4" style="text-align:center">
							위치 정보를 입력한 후에 조회해 주세요.
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
			
		</tbody>
		
	</table>
</body>
</html>