<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.InputEmployee" %>
<%
InputEmployee inputEmp = (InputEmployee)session.getAttribute("inputEmp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/kaigoMemo.css">
<title>介護メモ</title>
</head>
<body>
<h1>職員名登録確認画面</h1>
	<form action="EmployeeRegisterServlet" method="post">
	会社名　　　：<%= inputEmp.getCompanyName() %><br>
	施設名　　　：<%= inputEmp.getFacilityName() %><br>
	サービス形態：<%= inputEmp.getType() %><br>
	職員名　　　：<input type="text" name="name" value="<%= inputEmp.getName() %>"><br>
	<input type="hidden" name="action" value="employeeCheckOK">
	<input type="submit" value="職員名を登録する">
	</form>
</body>
</html>