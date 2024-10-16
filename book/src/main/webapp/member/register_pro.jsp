<%@page import="dao.MemberDAO"%>
<%@page import="dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//1. 가져올 값(== 넘어오는 값)이 있는지 확인
	MemberDTO insertDto = new MemberDTO();
	insertDto.setUserid(request.getParameter("userid"));
	insertDto.setName(request.getParameter("name"));
	insertDto.setPassword(request.getParameter("password"));
// 2. DB 작업

	MemberDAO dao = new MemberDAO();
	int insertRow = dao.insert(insertDto);

//4. 페이지 이동 ==1 (회원가입 성공) login.jsp
//				==0 (실패) register.jsp
	if(insertRow==1){
		response.sendRedirect("login.jsp");
	}else if(insertRow==0){
		response.sendRedirect("register.jsp");
	}

%>