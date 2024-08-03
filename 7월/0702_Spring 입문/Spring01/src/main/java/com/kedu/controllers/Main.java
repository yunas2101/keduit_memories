package com.kedu.controllers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.kedu.interfaces.Tv;

public class Main {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new GenericXmlApplicationContext("application-context.xml");
		// IOC :(Inversion Of Control) : �ν��Ͻ��� ���� �� ������ �����ڰ� �ƴ� Spring Container�� ����ϴ� ���
		// 								 ���� �з� �� ���� �δ��� �ٿ��� �� ����.
		// Dependency Lookup / Dependency Injection : Spring�� ���� �����Ǵ� �ν��Ͻ��� �����ڰ� ����ϱ� ���� �� ���� ���
		
		// getBean�� ���Դٴ� ���� Dependency Lookup�� �ϰ� �� ����
		
		Tv tv = ctx.getBean(Tv.class); // = Tv �ڷ���
		// ������ �����̳ʾ�! TvŸ���� Bean �� �����ͺ� 
//		tv.channelUp();
//		tv.volumeDown();

		System.out.println(tv.getChannel() + " : " + tv.getVolume());

	}
	
	
	
}
