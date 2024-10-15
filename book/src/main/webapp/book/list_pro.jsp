<%@page import="dto.BookDTO"%>
<%@page import="java.util.List"%>
<%@page import="dao.BookDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	BookDAO dao = new BookDAO();
	List<BookDTO> list = dao.getList();
	
	//이동방식
	// response.sendRedirect()
	// 			.forword()
	
	// 어떤 특정 값(객체)을 다른 페이지(jsp.servlet)들과 공유
	// 1. session session.setAttribute("list", list); + 페이지 이동(이동방식 제한조건 없음, 대부분 sedRedirect)
	// 2. request request.setAttribute("list", list); + 페이지 이동(반드시 forward() 방식=> 이동방식 제한으로 사용할 수 있는 페이지도 제한)
	request.setAttribute("list", list);
	pageContext.forward("list.jsp");
%>