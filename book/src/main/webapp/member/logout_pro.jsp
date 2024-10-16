<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
// 로그아웃 : 세션 정보 제거
session.removeAttribute("loginDto");
response.sendRedirect("../include/index.jsp");
%>>
