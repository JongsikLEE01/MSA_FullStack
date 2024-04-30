<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
</head>
<body>
	<h1>회원 가입</h1>
	<h3>유효성 검사</h3>
	<form name="joinForm" onsubmit="return checkJoin()" action="valodation02_pro.jsp" method="post">
		<p>아이디 : <input type="text" name="id" maxlength="20"/></p>
		<p>비밀번호 : <input type="password" name="pw"/></p>
		<p>이름 : <input type="text" name="name" /></p>
		<p>나이 : <input type="text" name="age" onkeyup="checkNumber()"/></p>
		<p><input type="submit" value="회원가입"></p>
	</form>
	
	<script>
		// 회원 가입 유효성 검사
		function checkJoin(){
			let form = document.joinForm
	
			let id = form.id.value
			let pw = form.pw.value
			
			// 아이디
			if(id == ''){
				alert("아이디를 입력해주세요")
				form.id.focus()		// id input 태그에 포커스
				return false
			}
			if(id.length < 6 || id.length > 20){
				alert('아이디는 6~20자 이내로 입력 가능합니다')
				form.id.select()	// id 입력 값을 선택
				return false
			}
			// 2. 비밀번호는 필수값, 6자 이상
			if(pw == ""){
				alert("비밀번호를 입력해주세요")
				form.pw.focus()		// pw input 태그에 포커스
				return false
			}
			if(pw.length < 6){
				alert('비밀번호는 6~20자 이내로 입력 가능합니다')
				form.pw.select()	// pw 입력 값을 선택
				return false
			}
			
			// 이름
			let name = form.name.value
			if(name == ""){
				alret('이름을 입력해주세요')
				form.name.focus()
				return false
			}
			if( !isNaN(name.substr(0,1)) ){
				alert('이름은 숫자로 시작할 수 없습니다')
				form.name,select()
				return false
			}	
			return true
		}
		
		function checkNumber(){
			let form = document.joinForm
			
			console.log(event.ketCode)
			
			// 숫자가 아닌 경우
			if(!(event.keyCode >= 48 && event.keyCode <= 57) ){
				alert('숫자만 입력 가능합니다')
				event.returnValue = false
				form.age.value = ''
			}
		}
	</script>
</body>
</html>