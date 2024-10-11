<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form action="join.jsp" method="post">
	<div>
		<label for="">아이디</label>
		<input type="text" name="id" id="id" /><br>
		<label for="">비밀번호</label>
		<input type="password" name="pw" id="pw" /><br>
		<label for="">이름</label>
		<input type="text" name="name" id="name" /><br>
		<label for="">전화번호</label>
		<input type="text" name="phone" id="phone" />	
	</div>
	<div>
		<button>전송</button>
	</div>
</form>