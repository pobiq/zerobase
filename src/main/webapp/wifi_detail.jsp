<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>와이파이 정보 구하기</title>
	<script>
		function bookMarkAdd() {
			var form = document.getElementById("bookMarkGroupForm");
			
			var id = form.id.value;
			
			if(id == "") {
				alert("북마크 그룹 이름을 선택하세요");
				form.id.focus();
				return;
			}
			
			form.submit();
		}
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
		
	</style>
</head>
<body>
<h1>와이파이 정보 구하기</h1>
	<jsp:include page="${pageContext.request.contextPath}/common/menus.jsp"/>
	
	<form action="/Servlet" method="POST" id="bookMarkGroupForm">
		<input type="hidden" name="command" value="bookMarkAdd.do">
		<input type="hidden" name="mgr_no" value="${detail.mgr_no}">
		<input type="hidden" name="main_nm" value="${detail.main_nm}">
		<div id="bookToolbar">
			<select name="id">
				<option selected value="">북마크 그룹 이름 선택</option>
				<c:if test="${not empty groupList}">
					<c:forEach var="item" items="${groupList}">
						<option value="${item.id}">${item.name}</option>
					</c:forEach>
				</c:if>
			</select>
			<input type="button" value="북마크 추가하기" onclick="bookMarkAdd();">
		</div>
	</form>
	
	<table id="list">
		<colgroup>
            <col width="20%;">
            <col width="80%;">
        </colgroup>
		<thead>
		</thead>
		<tbody>
			<tr>
				<th>거리(Km)</th>
				<td>${detail.km}</td>
			</tr>
			<tr>
				<th>관리번호</th>
				<td>${detail.mgr_no}</td>
			</tr>
			<tr>
				<th>자치구</th>
				<td>${detail.wrdofc}</td>
			</tr>
			<tr>
				<th>와이파이명</th>
				<td><a href="/Servlet?command=wifiDetail.do&mgr_no=${detail.mgr_no}&lat=35.28423080782676&lnt=127.29030792746354">${detail.main_nm}</a></td>
			</tr>
			<tr>
				<th>도로명주소</th>
				<td>${detail.adres1}</td>
			</tr>
			<tr>
				<th>상세주소</th>
				<td>${detail.adres2}</td>
			</tr>
			<tr>
				<th>설치위치(층)</th>
				<td>${detail.instl_floor}</td>
			</tr>
			<tr>
				<th>설치유형</th>
				<td>${detail.instl_ty}</td>
			</tr>
			<tr>
				<th>설치기관</th>
				<td>${detail.instl_mby}</td>
			</tr>
			<tr>
				<th>서비스구분</th>
				<td>${detail.svc_se}</td>
			</tr>
			<tr>
				<th>망종류</th>
				<td>${detail.cmcwr}</td>
			</tr>
			<tr>
				<th>설치년도</th>
				<td>${detail.cnstc_year}</td>
			</tr>
			<tr>
				<th>실내외구분</th>
				<td>${detail.inout_door}</td>
			</tr>
			<tr>
				<th>WIFI접속환경</th>
				<td>${detail.remars3}</td>
			</tr>
			<tr>
				<th>X좌표</th>
				<td>${detail.lat}</td>
			</tr>
			<tr>
				<th>Y좌표</th>
				<td>${detail.lnt}</td>
			</tr>
			<tr>
				<th>작업일자</th>
				<td>${detail.work_dttm}</td>
			</tr>
		</tbody>
		
	</table>
</body>
</html>