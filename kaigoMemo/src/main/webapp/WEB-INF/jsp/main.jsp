<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Memos" %>
<%@ page import="model.Employees" %>
<%
// 日時表示用フォーマット
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
//申し送り一覧
List<Memos> memosList = (List<Memos>)session.getAttribute("memosList");
//ログイン中の職員
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

<h1>申し送り一覧</h1>

<table>
    <thead>
        <tr>
            <th>日時</th>
            <th>件名</th>
            <th>記入者</th>
        </tr>
    </thead>
    <tbody>
<%
    if (memosList != null && !memosList.isEmpty()) {
        for (Memos memos : memosList) {
%>
		 <tr>
 		     <td><%= memos.getDateTime().format(formatter) %></td>
  		 	 <td>
   			 <a href="MemoViewServlet?id=<%= memos.getId() %>">
    		 <%= memos.getTitle() %>
     		 </a>
    	     </td>
  	         <td><%= memos.getName() %></td>
	 	 </tr>
<%
        }
    } else {
%>
        <tr>
            <td colspan="4">データがありません</td>
        </tr>
<%
    }
%>
    </tbody>
</table>
    <!-- 左: 現在ログインしている職員 -->
    <div>
        現在ログイン: <strong><%= emp.getName() %></strong>
    </div>

    <!-- 中央: 職員切り替えリンク -->
    <div>
        <a href="EmployeeSwitchServlet" style="color: green; text-decoration: none; margin-right: 20px;">職員切り替え</a>
        <a href="MemoPostServlet" style="color: green; text-decoration: none;">申し送りを記入する</a>
    </div>
</div>

</body>
</html>