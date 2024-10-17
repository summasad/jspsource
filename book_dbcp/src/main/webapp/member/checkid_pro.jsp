<%@page import="dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//1. 가져올 값(== 넘어오는 값)이 있는지 확인
	String userid = request.getParameter("userid");
	System.out.println(userid);
// 2. DB 작업
	MemberDAO dao = new MemberDAO();
	boolean dupId = dao.dupId(userid);
	
	if(dupId){ //true 중복아이디 없음
		out.print("true");
	} else{
		out.print("false");
	}
%>