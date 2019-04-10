package com.itbank.test;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

// ������ ������ ���� �̺�Ʈ�� �����Ͽ� ���ϴ� �۾��� ����
public class MyContextLoaderListener implements ServletContextListener {
	// ������ ������ �� ȣ��
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("�� �����ƾ�");
		String msg = sce.getServletContext().getInitParameter("contextConfigLocation");
		System.out.println(msg);
	}

	// ������ ����� �� ȣ��
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("�� �׾���");
	}
}