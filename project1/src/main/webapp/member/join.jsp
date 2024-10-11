<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String name = request.getParameter("name");
	String phone = request.getParameter("phone");
%>
<ul>
	<li>아이디 : <%=id  %></li>
	<li>비밀번호 : <%=pw  %></li>
	<li>이름 : <%=name  %></li>
	<li>전화번호 : <%=phone  %></li>
</ul>
	
</body>
</html>