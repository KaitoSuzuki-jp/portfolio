<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Companies,model.Employees, java.util.List" %>
<%
Companies com = (Companies)session.getAttribute("com");
List<Employees> empList = (List<Employees>)session.getAttribute("empList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/kaigoMemo.css">
<title>介護メモ</title>
</head>
<body>

<h1>職員名入力画面</h1>

<form action="EmployeeServlet" method="post">
会社名　　　：<%= com.getCompanyName() %><br>
施設名　　　：<%= com.getFacilityName() %><br>
サービス形態：<%= com.getType() %><br>
職員名　　　：
<select name="name">
<% if(empList != null && !empList.isEmpty()){ 
       for(Employees e : empList){ %>
           <option value="<%= e.getName() %>"><%= e.getName() %></option>
<%   } 
   } else { %>
           <option value="">職員が選択されていません</option>
<% } %>
</select>
<input type="submit" value="進む">
</form>

<a href="EmployeeRegisterServlet">職員登録</a>

</body>
</html>