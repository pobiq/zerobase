<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>와이파이 정보 구하기</title>
	<script>
	function bookMarkGroupEdit() {
		var form = document.getElementById("bookMarkGroupEditForm");
		
		var name = form.name.value;
		var preference = form.preference.value;
		
		if(name == null || name == "") {
			alert("북마크 이름을 입력하세요.");
			form.name.focus();
			return;
		}
		
		if(preference == null || preference == "") {
			alert("순서(숫자)를 입력하세요.");
			form.preference.focus();
			return;
		}
		
		preference = Number(preference);
		
		if(!Number.isInteger(preference) || preference <= 0) {
			alert("순서 값이 양의 정수가 아닙니다.");
			form.preference.focus();
			return;
		}
		
		form.submit();
		
	}
	</script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu.css">
	<style>
		
		#list {
			width : 100%;
			border : 1px solid #E3DBDE;
			border-spacing: 0px;
		}
		
		#list > tbody > tr:nth-child(even) > td {
			background-color : #F2F2F2;
		}
		
		#list > tbody > tr > th {
			padding : 5px 5px;
			background-color : #00AC6F;
			color : white;
		}
		
		#list > tbody > tr:not(:last-child) > th {
			border-bottom : 1px solid white;
		}
		
		#list > tbody > tr > td {
			padding : 10px 0px;
			border : 1px solid #E3DBDE;
			text-align : left;
			padding-left : 5px;
		}
		
		#list > tbody > tr:last-child > td {
			text-align : center;
		}
		
	</style>
</head>
<body>
	<h1>북마크 그룹 수정</h1>
	<jsp:include page="${pageContext.request.contextPath}/common/menus.jsp"/>
	
	<form action="/Servlet" method="POST" id="bookMarkGroupEditForm">
		<input type="hidden" name="command" value="bookMarkGroupEdit.do">
		<input type="hidden" name="id" value="${detail.id}">
		<table id="list">
			<colgroup>
	            <col width="20%;">
	            <col width="80%;">
	        </colgroup>
			<thead>
			</thead>
			<tbody>
				<tr>
					<th>북마크 이름</th>
					<td><input type="text" id="name" name="name" value="${detail.name}" maxlength="25"></td>
				</tr>
				<tr>
					<th>순서</th>
					<td><input type="text" id="preference" name="preference" value="${detail.preference}" maxlength="10"></td>
				</tr>
				<tr>
					<td colspan="2">
						<a href="/Servlet?command=bookMarkGroup.do">돌아가기</a> |  
						<input type="button" value="수정" onclick="bookMarkGroupEdit();">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>