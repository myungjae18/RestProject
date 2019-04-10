package com.itbank.test;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

// 서버가 가동될 때의 이벤트를 감지하여 원하는 작업을 수행
public class MyContextLoaderListener implements ServletContextListener {
	// 서버가 가동될 때 호출
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("나 가동됐어");
		String msg = sce.getServletContext().getInitParameter("contextConfigLocation");
		System.out.println(msg);
	}

	// 서버가 종료될 떄 호출
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("나 죽었어");
	}
}