<%@page import="dao.MemberDAO"%>
<%@page import="dto.ChangeDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//1. 가져올 값(== 넘어오는 값)이 있는지 확인
	ChangeDTO changeDto = new ChangeDTO();
	changeDto.setCurrentPassword(request.getParameter("current_password"));
	changeDto.setUserid(request.getParameter("userid"));
	changeDto.setChangePassword(request.getParameter("change_password"));
	
	
// 2. DB 작업
	MemberDAO dao = new MemberDAO();
	int updateRow = dao.update(changeDto);

//4. 페이지 이동
	if(updateRow==0){
		response.sendRedirect("info.jsp");
	}else if(updateRow==1){
		session.invalidate(); //모든 세션, 세션지정 - session.removeAttribute("loginDto");
		response.sendRedirect("login.jsp");
	}

%>