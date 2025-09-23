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
<h1>会社名・施設名・サービス形態　新規登録画面</h1>
<form action="CompanyRegisterServlet" method="post">
会社名　　　：<input type="text" name="company" placeholder="○○株式会社">
施設名　　　：<input type="text" name="facility">
サービス形態：<input type="text" name="type" placeholder="デイサービス">
<input type="hidden" name="action" value="companyCheck">
<input type="submit" value="登録する">
</form>
</body>
</html>