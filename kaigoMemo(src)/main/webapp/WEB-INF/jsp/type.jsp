<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.InputCompany" %>
<%
InputCompany inputCom = (InputCompany)session.getAttribute("inputCom");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/kaigoMemo.css">
<title>介護メモ</title>
</head>
<body>
	<h1>サービス形態入力画面</h1>
	<form action="TypeServlet" method="post">
	会社名　　　：<%= inputCom.getCompanyName() %><br>
	施設名　　　：<%= inputCom.getFacilityName() %><br>
	サービス形態：<input type="text" name="type"><br>
	<input type="submit" value="次へ進む">
	</form>
</body>
</html>