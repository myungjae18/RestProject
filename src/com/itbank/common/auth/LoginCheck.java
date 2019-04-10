package com.itbank.common.auth;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.itbank.model.domain.Admin;

// 로그인이 필요한 코드마다 로직을 작성하지 말고 공통 코드로 분리시켜 AOP를 적용
@Aspect
public class LoginCheck {
	@Pointcut("execution(public * com.itbank.controller.AdminController..main*(..))")
	public void loginCut() {

	}

	@Around("loginCut()")
	public String CheckSession(ProceedingJoinPoint joinPoint) {
		String viewName = null;
		HttpServletRequest request = null;

		// 세션 존재 여부 확인
		Object[] args = joinPoint.getArgs();
		for (int i = 0; i < args.length; i++) {
			// args의 자료형 검사
			if (args[i] instanceof HttpServletRequest) {
				request = (HttpServletRequest) args[i]; // 요청 객체만 담기
			}
		}
		// 세션에 담겨진 것 체크
		if (request.getSession().getAttribute("admin") == null) {
			System.out.println("인증되지 않은 사용자입니다.");
			viewName = "redirect:/admin/error/accessDeny.jsp";
		} else {
			Admin admin = (Admin) request.getSession().getAttribute("admin");
			System.out.println("올바른 사용자입니다!" + admin.getId());
			viewName = "admin/index";
		}
		return viewName;
	}
}
