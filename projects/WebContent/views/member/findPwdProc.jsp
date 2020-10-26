<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	int sq = (int) request.getAttribute("sq");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"
		integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
		crossorigin="anonymous">
</script>
<script>
	function checkValidate() {
		var pwd = $('#pwd');
		var confirm_pwd = $('#confirm_pwd');
		
		if (!pwd.val()) {
			alert("비밀번호를 입력해 주세요.");
			name.focus();
			return
		}
		
		if (!confirm_pwd.val()) {
			alert("비밀번호 확인을 입력해 주세요.");
			name.focus();
			return
		}
		
		if (pwd.val() != confirm_pwd.val()) {
			alert("비밀번호가 다릅니다.")
			pwd.val('');
			confirm_pwd.val('');
			confirm_pwd.focus();
			return;
		}
		
		var regExpPwd = new RegExp("^.{4,20}$", "g");
		if (regExpPwd.exec(pwd.val()) == null) {
			alert("비밀번호는 4~20자로 입력해 주세요.");
			pwd.val('');
			confirm_pwd.val('');
			pwd.focus();
			return;
		}
		
		$('#find').submit();
	}
</script>
</head>
<body>
<h1>새로운 비밀번호 입력</h1>
<form method="post" action="findPwdAfter.do" id="find">
	비밀번호 <input type="password" name="pwd" id="pwd" maxlength="20">
	비밀번호확인 <input type="password" name="confirm_pwd" id="confirm_pwd" maxlength="20">
	<button type="button" onclick="checkValidate()">입력</button>
	<input type="hidden" value="<%= sq %>" name="mb_sq">
</form>
</body>
</html>