<%@page import="dto.MemberDTO"%>
<%@page import="dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
//1. 가져올 값(== 넘어오는 값)이 있는지 확인
	
	MemberDTO loginDto = new MemberDTO();
	loginDto.setUserid(request.getParameter("userid"));
	loginDto.setPassword(request.getParameter("password"));
	
	
// 2. DB 작업
	MemberDAO dao = new MemberDAO();
	MemberDTO dto = dao.isLogin(loginDto);
	

//3. 결과 값을 공유
//session.setAttribute()	

//4. 페이지 이동
// ==null : login.jsp
// !=null : list_pro.jsp
	if(dto!=null){
		//서버쪽에서 정보 보관
		session.setAttribute("loginDto", dto);
		response.sendRedirect("../book/list_pro.jsp");
	}else {
		response.sendRedirect("login.jsp");
	}

%>>