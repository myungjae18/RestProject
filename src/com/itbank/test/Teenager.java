package com.itbank.test;

import org.springframework.stereotype.Component;

@Component
public class Teenager implements Student {
	@Override
	public void getUp() {
		System.out.println("���");
	}

	@Override
	public void goSchool() {
		System.out.println("�");
	}

	@Override
	public void study() {
		System.out.println("����");
	}
}