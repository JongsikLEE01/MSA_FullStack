<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정규 표현식</title>
</head>
<body>
	<h1>정규표현식</h1>
	<form name="form" onsubmit="return checkForm()">
		<p>제목 : <input type="text" name="title"></p>
		<p><input type="submit" value="등록"/></p>
	</form>
	
	<script type="text/javascript">
		function checkForm(){
			let regExp = /Java/i	// Java 문자열을 대소문자 없이 검사
			let str = documnt.form.title.value
			let res = regExp.exec(str)
			
			// exec() : 정규 표현식에 부합한 문자열 반환
			// test() : 정규 표현식에 부합한지 여부를 반환
			if(regExp.test(str)){
				alert(res[0])
			}
			return false
		}
	</script>
</body>
</html>