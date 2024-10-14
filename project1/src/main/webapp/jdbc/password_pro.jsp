<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dto.ChangeDTO"%>
<%@page import="dao.MemberDAO"%>
<%
	//password.jsp 에서 넘긴 값 가져와서 ChangeDTO에 담기
	
	ChangeDTO changeDto = new ChangeDTO();
	changeDto.setUserid(request.getParameter("userid"));
	changeDto.setCurrentPassword(request.getParameter("current_password"));
	changeDto.setChangePassword(request.getParameter("change_password"));
	
	MemberDAO dao = new MemberDAO();
	int updateRow = dao.update(changeDto);
	
	//updateRow = 1 일 때 세션 제거 / login.jsp 로 이동
	if(updateRow==1){
		session.invalidate();
		response.sendRedirect("login.jsp");
	}else{
		//updateRow = 0 일 때 password.jsp 로 이동
		response.sendRedirect("password.jsp");
	}		
	

%>