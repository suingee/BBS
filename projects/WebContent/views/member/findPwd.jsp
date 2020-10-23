<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.slim.js"
	integrity="sha256-DrT5NfxfbHvMHux31Lkhxg42LY6of8TaYyK50jnxRnM="
	crossorigin="anonymous">
</script>
<script>
	function validateCheck() {
		var name = $('#name').val();
		var id = $('#id').val();
		
		if (!name) {
			alert("이름을 입력해 주세요.");
			$('#name').focus();
			return false;
		}
		
		if (!id) {
			alert("아이디를 입력해 주세요.");
			$('#id').focus();
			return false;
		}
		
		$('#find').submit();
	}
</script>
</head>
<body>
<form action="/findPwdProc.do" method="post" id="find">
	이름 : <input type="text" name="name" id="name">
	아이디 : <input type="text" name="id" id="id">
	<button type="button" onclick="validateCheck()">찾기</button>
	<button type="button" onclick="location.href='/'">취소</button>
</form>
</body>
</html>