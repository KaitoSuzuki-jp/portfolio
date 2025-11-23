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
	<h1>会社名確認画面</h1>
	<p>会社名：<%= inputCom.getCompanyName() %>でよろしいですか？</p><br>
	<a href="CompanyServlet">次へ進む</a>
	<a href="KaigoMemo">入力画面へ戻る</a>
</body>
</html>