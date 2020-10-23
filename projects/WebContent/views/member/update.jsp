<%@ page import="com.kb.www.bbs.vo.MemberVo" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	MemberVo vo = (MemberVo) request.getAttribute("memberVo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<div>
	이름 : <%= vo.getNm() %>
</div>


</body>
</html>