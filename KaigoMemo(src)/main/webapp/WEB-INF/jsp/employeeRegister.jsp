<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Companies" %>
<%
Companies com = (Companies)session.getAttribute("com");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/kaigoMemo.css">
<title>介護メモ</title>
</head>
<body>
	<h1>職員名登録画面</h1>
	<form action="EmployeeRegisterServlet" method="post">
	会社名　　　：<%= com.getCompanyName() %><br>
	施設名　　　：<%= com.getFacilityName() %><br>
	サービス形態：<%= com.getType() %><br>
	職員名　　　：<input type="text" name="name"><br>
	    <input type="hidden" name="action" value="employeeCheck">
	<input type="submit" value="進む">
	</form>
</body>
</html>