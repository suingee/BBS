<%@ page import="com.kb.www.bbs.vo.BoardVo"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<BoardVo> list = (ArrayList<BoardVo>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
	<tr>
		<td>번 호</td>
		<td>제 목</td>
		<td>작성자</td>
		<td>작성일</td>
		<td>조 회</td>
	</tr>
	<%
		if (list.size() > 0) {
	%>
		<%
			for (BoardVo vo : list) {
		%>
			<tr>
				<td><%= vo.getDb_sq() %></td>
				<td><%= vo.getSj() %></td>
				<td><%= vo.getId() %></td>
				<td><%= vo.getDttm() %></td>
				<td><%= vo.getHit() %></td>
			</tr>
		<%
			}
		%>
	<%
		} else {
	%>
		<col 
	<%
		}
	%>
</table>
	<button onclick="location.href='write.do'">글쓰기</button>
		

</body>
</html>