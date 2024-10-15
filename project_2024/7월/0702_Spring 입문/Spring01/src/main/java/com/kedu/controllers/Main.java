package com.kedu.controllers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.kedu.interfaces.Tv;

public class Main {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new GenericXmlApplicationContext("application-context.xml");
		// IOC :(Inversion Of Control) : 인스턴스의 생성 및 관리를 개발자가 아닌 Spring Container가 담당하는 기법
		// 								 개발 분량 및 관리 부담을 줄여줄 수 있음.
		// Dependency Lookup / Dependency Injection : Spring에 의해 관리되는 인스턴스를 개발자가 사용하기 위한 두 가지 기법
		
		// getBean이 나왔다는 것은 Dependency Lookup을 하게 된 것임
		
		Tv tv = ctx.getBean(Tv.class); // = Tv 자료형
		// 스프링 컨테이너야! Tv타입인 Bean 좀 가져와봐 
//		tv.channelUp();
//		tv.volumeDown();

		System.out.println(tv.getChannel() + " : " + tv.getVolume());

	}
	
	
	
}
