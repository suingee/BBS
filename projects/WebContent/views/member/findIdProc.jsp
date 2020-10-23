<%@ page import="com.kb.www.bbs.vo.MemberVo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberVo vo = (MemberVo) request.getAttribute("memberVo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>아이디 찾기</h1>
회원가입 시 사용한 아이디는 <%= vo.getId() %> 입니다.

<button type="button" onclick="location.href='/'">로그인 화면으로 돌아가기</button>
</body>
</html>