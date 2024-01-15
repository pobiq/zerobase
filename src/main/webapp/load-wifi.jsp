<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>와이파이 정보 구하기</title>
	<style>
		h1 {
			text-align : center;
		}
		div {
			width : 100%;
			text-align : center;
		}
	</style>
</head>
<body>
<h1>
	<%=request.getAttribute("total_cnt")%>개의 WIFI 정보를 정상적으로 저장하였습니다.
</h1>
<div>
	<a href="/Servlet?command=main.do">홈 으로 가기</a>
</div>
</body>
</html>