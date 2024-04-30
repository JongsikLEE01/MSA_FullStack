<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="form" onsubmit="return checkForm()">
        <p>제목 : <input type="text" name="title" /> </p>
        
        <p>아이디 : <input type="text" name="id" /> </p>
        <p>이름 : <input type="text" name="name" /> </p>
        
        <p>전화번호 : <select name="phone1">
                        <option value="010">010</option>
                        <option value="011">011</option>
                        <option value="016">016</option>
                        <option value="017">017</option>
                        <option value="019">019</option>
                    </select> 
                    - <input type="text" maxlength="4" size="4" name="phone2"> 
                    - <input type="text" maxlength="4" size="4" name="phone3">
                    
        <p>이메일 : <input type="text" name="email" /> </p>
        
        <p><input type="submit" value="등록" /> </p>
    </form>
    
    <script>
    	// 회원가입 정규 표현식으로 유효성 검사
    	function checkForm(){
    		// 아이디 -> 영문자, 한글로 시작
    		let regExpId = /^[a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]+$/
    		// 이름 -> 한글
    		let regExpNmae = /^[가-힣]*$/
    		// 전화번호 [3자리]-[3~4자리]-[4자리] 숫자
    		let regExpPhone = /^\d{3}-\d{3,4}-\d{4}$/
    		// 이메일 [숫자,영문자]@[숫자, 영문자].~~.[영문자 2~3자리]
    		let regExpEmail = /^[0-9a-zA-Z](-_\.)?[0-9a-zA-Z]*@[0-9a-zA-Z](-_\.)?[0-9a-zA-Z]*\.[a-zA-Z]{2,3}$/i
    		// 부분 분석
    		// /^[0-9a-zA-Z]	: 시작은 숫자 또는 영어
    		// (-_\.)?			: 중간에 -, _, . 0또는 1회 가능
    		// [0-9a-zA-Z]*		: 그 후 숫자 영어가 0 또는 1이상
    		
    		// 전체분석
    		// 1. [0-9a-zA-Z](-_\.)?[0-9a-zA-Z]*
    		// 2. @
    		// 3. [0-9a-zA-Z](-_\.)?[0-9a-zA-Z]*\.
    		// 4. .
    		// 5. [a-zA-Z]{2,3}$/i
    		
    		let id = form.id.value
    		let name = form.name.value
    		let phone = form.phone1.vlaue+ "-" +form.phone2.vlaue+ "-" +form.phone3.vlaue
    		let email = form.email.vlaue
    		
    		// test : 부합시 true, 부적합시 false
    		if(!regExpId.test(id)){
    			alert('아이디 형식에 맞게 입력해주세요')
    			return false
    		}
    		
    		if(!regExpName.test(name)){
    			alert('이름 형식에 맞게 입력해주세요')
    			return false
    		}
    		
    		if(!regExpPhone.test(phone)){
    			alert('전화 번호 형식에 맞게 입력해주세요')
    			return false
    		}
    		
    		if(!regExpEmail.test(email)){
    			alert('이메일 형식에 맞게 입력해주세요')
    			return false
    		}
    		
    	}
    </script>
</body>
</html>