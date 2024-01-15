<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>와이파이 정보 구하기</title>
	<script>
		function myLocation() {
			var lat = document.getElementById("lat");
			var lnt = document.getElementById("lnt");
			lat.value = 35.28423080782676;
			lnt.value = 127.29030792746354;
		}
		
		function getAroundLocation() {
			var form = document.getElementById("info");
			
			var lat = form.lat.value;
			var lnt = form.lnt.value;
			
			if(lat == 0.0 && lnt == 0.0) {
				alert("내 위치 가져오기 버튼을 누르세요.");
				return;
			}
			
			form.submit();
		}
	</script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/menu.css">
	<style>
		
		#input_box {
			margin-bottom : 10px; 
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
		
	</style>
</head>
<body>
	<h1>와이파이 정보 구하기</h1>
	<jsp:include page="${pageContext.request.contextPath}/common/menus.jsp"/>
	
	<form action="/Servlet" id="info" name="info" method="GET">
		<div id="input_box">
			<input type="hidden" name="command" value="main.do">
			LAT: <input type="text" id="lat" name="lat" readonly="readonly" value="${empty lat ? 0.0 : lat}"> , 
			LNT: <input type="text" id="lnt" name="lnt" readonly="readonly" value="${empty lnt ? 0.0 : lnt}">
			<input type="button" name="myLocationButton" value="내 위치 가져오기" onclick="myLocation();">
			<input type="button" value="근처 WIPI정보 보기" onclick="getAroundLocation()">
		</div>
	</form>
	
	<table id="list">
		<colgroup>
            <col width="5%;">
            <col width="8%;">
            <col width="3%;">
            <col width="9%;">
            <col width="9%;">
            <col width="16%;">
            <col width="4%;">
            <col width="5%;">
            <col width="5%;">
            <col width="4%;">
            <col width="5%;">
            <col width="3%;">
            <col width="3%;">
            <col width="4%;">
            <col width="5%;">
            <col width="5%;">
            <col width="7%;">
        </colgroup>
		<thead>
			<tr>
				<th>거리(Km)</th>
				<th>관리번호</th>
				<th>자치구</th>
				<th>와이파이명</th>
				<th>도로명주소</th>
				<th>상세주소</th>
				<th>설치위치(층)</th>
				<th>설치유형</th>
				<th>설치기관</th>
				<th>서비스구분</th>
				<th>망종류</th>
				<th>설치년도</th>
				<th>실내외구분</th>
				<th>WIFI접속환경</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>작업일자</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty wifiInfoList}">
					<c:forEach var="item" items="${wifiInfoList}">
						<tr>
							<td>${item.km}</td>
							<td>${item.mgr_no}</td>
							<td>${item.wrdofc}</td>
							<td><a href="/Servlet?command=wifiDetail.do&mgr_no=${item.mgr_no}&lat=${lat}&lnt=${lnt}">${item.main_nm}</a></td>
							<td>${item.adres1}</td>
							<td>${item.adres2}</td>
							<td>${item.instl_floor}</td>
							<td>${item.instl_ty}</td>
							<td>${item.instl_mby}</td>
							<td>${item.svc_se}</td>
							<td>${item.cmcwr}</td>
							<td>${item.cnstc_year}</td>
							<td>${item.inout_door}</td>
							<td>${item.remars3}</td>
							<td>${item.lat}</td>
							<td>${item.lnt}</td>
							<td>${item.work_dttm}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="17" style="text-align:center">
							위치 정보를 입력한 후에 조회해 주세요.
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
			
		</tbody>
		
	</table>
</body>
</html>