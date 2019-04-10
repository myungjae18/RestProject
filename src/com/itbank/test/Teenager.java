package com.itbank.test;

import org.springframework.stereotype.Component;

@Component
public class Teenager implements Student {
	@Override
	public void getUp() {
		System.out.println("기상");
	}

	@Override
	public void goSchool() {
		System.out.println("등교");
	}

	@Override
	public void study() {
		System.out.println("공부");
	}
}