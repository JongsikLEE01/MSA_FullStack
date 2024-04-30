package com.jslee.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//import com.jslee.spring.config.config;
import com.jslee.spring.dto.Board;
import com.jslee.spring.dto.Reply;
import com.jslee.spring.dto.User;

@Controller
public class BeanController {
	
	// @RequestMapping
	// * /bean으로 GET 방식 요청이 오면 실행되는 메소드로 지정
	@RequestMapping(value = "/bean", method = RequestMethod.GET)
	public String bean(Model model) {
		// 패키지 이름 지정을 지정하여 컨텍스트 객체 생성
		ApplicationContext context = new AnnotationConfigApplicationContext("com.jslee.spring");
		// 컨텍스트로부터 등록된 빈을 가져오기
		Board board = context.getBean(Board.class);
		model.addAttribute("board", board);
		
//		// 자바 빈 설정 파일로 컨텍스트 객체 생성
//		ApplicationContext context2 = new AnnotationConfigApplicationContext(config.class);
//		Reply reply = context2.getBean(Reply.class);
//		model.addAttribute("reply" ,reply);
		
		// XML 파일 경로 지정
//		ApplicationContext context3 = new ClassPathXmlApplicationContext("main/java/com/jslee/WEB-INF/config/config.xml");
//		User user = context.getBean(User.class);
//		model.addAttribute("user" ,user);
		
		return "bean";
	}
}