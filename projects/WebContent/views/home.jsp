<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kb.www.common.LoginManager" %>
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
<a href="list.do">게시판 이동</a>
<%
	if (id == null) {
%>
<a href="memberJoin.do">회원가입</a>
<a href="memberLogin.do">로그인</a>
<a href="findId.do">아이디 찾기</a>
<a href="findPwd.do">비밀번호 찾기</a>
<%
	} else {
%>
<a href="memberLogout.do">로그아웃</a>
<a href="memberUpdate.do">회원정보수정</a>
<a href="memberDelete.do">회원탈퇴</a>
<%
	}
%>
</body>
</html>