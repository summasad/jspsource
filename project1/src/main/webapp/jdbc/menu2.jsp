<%@page import="dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "header.jsp"  %>>
<h3 class="mt-3">메뉴2</h3>
<%
	//세션에 담긴 값 가져오기
	MemberDTO loginDto = (MemberDTO)session.getAttribute("loginDto");
%>
<%if(loginDto != null) { %>
<div>
	<%-- 로그인을 한 경우 --%>
	<%=loginDto.getName() %>님 반갑습니다. 로그아웃
	<a class="btn btn-primary" href="">로그아웃</a>
	<a class="btn btn-success" href="">비밀번호 수정</a>
	<a class="btn btn-danger" href="">탈퇴</a>
</div>
<% } %>

<%@ include file = "footer.jsp"  %>>