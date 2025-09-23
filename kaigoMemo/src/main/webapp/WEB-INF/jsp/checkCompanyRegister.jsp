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
<h1>会社名・施設名・サービス形態　確認画面</h1>
<form action="CompanyRegisterServlet" method="post">
会社名　　　：<input type="text" name="company" value="<%= inputCom.getCompanyName() %>"><br>
施設名　　　：<input type="text" name="facility" value="<%= inputCom.getFacilityName() %>"><br>
サービス形態：<input type="text" name="type" value="<%= inputCom.getType() %>"><br>
<p>でよろしいですか？</p>
    <input type="hidden" name="action" value="companyCheckOK">
<input type="submit" value="会社名・施設名を登録する">
</form>

</body>
</html>