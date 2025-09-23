<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Companies,model.Employees,model.Memos,java.util.List,model.MemoPost" %>
<%
Companies com = (Companies)session.getAttribute("com");
Employees emp = (Employees)session.getAttribute("emp");
List<Memos> memosList = (List<Memos>)session.getAttribute("memosList");
MemoPost memoPost = (MemoPost)session.getAttribute("memoPost");
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
<h1>
<% if(com != null && com.getErrorMsg() != null){%>
<%=     com.getErrorMsg() %>
<% } else if (emp != null && emp.getErrorMsg() != null){ %>
<%=     emp.getErrorMsg() %>
<% } else if (memoPost != null && memoPost.getErrorMsg() != null){%>
<%=     memoPost.getErrorMsg() %>
<% } else if (memos != null && memos.getErrorMsg() != null) { %>
<%=     memos.getErrorMsg() %>
<% } else if (memosList == null || memosList.isEmpty()){ %>
<%=     "申し送りを取得できませんでした" %>
<% } %>
メイン画面に戻りますか？</h1>

<% if ((com == null || com.getErrorMsg() == null) && (emp == null || emp.getErrorMsg() == null)) { %>
<a href="ErrorServlet?action=MainServlet">メイン画面へ戻る</a><br>
<% } %>
<a href="ErrorServlet?action=StartServlet">スタート画面へ戻る</a>
</body>
</html>