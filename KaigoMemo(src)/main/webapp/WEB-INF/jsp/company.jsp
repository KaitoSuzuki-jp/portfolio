<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/kaigoMemo.css">
<title>介護メモ</title>
</head>
<body>
	<h1>介護メモ</h1>
	<form action="CompanyServlet" method="post">
	会社名：<input type="text" name="company" placeholder="○○株式会社">
	<input type="submit" value="次へ進む">
	</form>
	<a href="CompanyRegisterServlet" >会社・施設　新規登録画面</a>
</body>
</html>