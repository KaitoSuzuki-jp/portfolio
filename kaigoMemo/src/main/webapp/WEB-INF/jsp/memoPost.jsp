<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Employees" %>
<%
Employees emp = (Employees)session.getAttribute("emp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/kaigoMemo.css">
<title>介護メモ</title>
</head>
<body>
<h1>申し送り記入画面</h1>

<form action="MemoPostServlet" method="post">
件名　：<input type="text" name="title" required><br>
本文　：<textarea 
    name="memo" 
    required 
    maxlength="500"
    class="auto-resize"></textarea>
記入者：<%= emp.getName() %><br>
<input type="submit" value="申し送りを投稿する">
</form>
<a href="MainServlet">戻る</a>

<script>
document.addEventListener("input", function(e) {
  if (e.target.tagName.toLowerCase() === "textarea" &&
      e.target.classList.contains("auto-resize")) {
    e.target.style.height = "auto";
    e.target.style.height = (e.target.scrollHeight) + "px";
  }
});
</script>

</body>
</html>