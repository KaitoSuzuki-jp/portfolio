<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Memos" %>
<%
Memos memos = (Memos)session.getAttribute("memos");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/kaigoMemo.css">
<title>介護メモ</title>
</head>
<body>
<h1>以下の内容で申し送り内容を投稿しました</h1>

件名　：<%= memos.getTitle() %><br>
本文　：<%= memos.getMemo() %><br>
投稿者：<%= memos.getName() %><br>

<a href="CheckMemoPostServlet">メイン画面へ戻る</a>
</body>
</html>