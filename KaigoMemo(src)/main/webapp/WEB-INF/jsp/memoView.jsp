
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
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/memoView.css">
<title>介護メモ 詳細</title>
</head>
<body>

<%
    if (memos != null) {
%>
    <div class="memo-card">
        <h1>申し送り詳細</h1>
        <p><strong>タイトル：</strong> <%= memos.getTitle() %></p>
        <p><strong>内容：</strong> <%= memos.getMemo() %></p>
        <p><strong>作成者：</strong> <%= memos.getName() %></p>
        <p><strong>日時：</strong> <%= memos.getDateTime() %></p>
        <p style="text-align:center;"><a href="MainServlet">戻る</a></p>
    </div>
<%
    } else {
%>
    <p style="text-align:center;">申し送り情報が見つかりませんでした。</p>
<%
    }
%>

</body>
</html>