package com.jslee.spring.service;

import org.springframework.stereotype.Service;

// 빈 등록 어노테이션 ("빈이름")
// : * 빈이름 지정하지 않으면 클래스명으로 기본 지정
// 	 * '빈이름' 지정하면 @Qualifier("지정한 빈이름")으로 타입 기반 주입(빈 이름 지정해 주입)
@Service("C")
public class CServiceImpl implements MyService{
	@Override
	public void test() {
		System.out.println("CserviceImpl...");
	}
}