<%@page import="com.kb.www.common.LoginManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	LoginManager lm = LoginManager.getInstance();
	String id = lm.getMemberId(session);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%if (id == null) { %>
	<a href="memberJoin.do">회원가입</a>
	<a href="login.do">로그인</a>
	<%} else { %>
	<a href="logout.do">로그아웃</a>
	<a href="leave.do">회원탈퇴</a>
	<%} %>
</body>
</html>