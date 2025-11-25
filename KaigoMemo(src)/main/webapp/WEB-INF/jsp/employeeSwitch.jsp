<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Employees,java.util.List" %>
<%
Employees emp = (Employees)session.getAttribute("emp");
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
<h1>職員切り替え</h1>

<form action="EmployeeSwitchServlet" method="post">
会社名　　　：<%= emp.getCompanyName() %><br>
施設名　　　：<%= emp.getFacilityName() %><br>
サービス形態：<%= emp.getType() %><br>
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