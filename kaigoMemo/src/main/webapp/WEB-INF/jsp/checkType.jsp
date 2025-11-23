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
	<h1>サービス形態確認画面</h1>
	会社名　　　：<%= inputCom.getCompanyName() %><br>
	施設名　　　：<%= inputCom.getFacilityName() %><br>
	サービス形態：<%= inputCom.getType() %><br>
	<p>でよろしいですか？</p>
	<a href="CheckTypeServlet">職員選択へ進む</a>
	<a href="checkType?action=company">会社名入力画面へ戻る</a>
	<a href="checkType?action=facility">施設名入力画面へ戻る</a>
	<a href="checkType?action=type">サービス形態入力画面へ戻る</a>
</body>
</html>