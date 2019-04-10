package com.itbank.common.auth;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.itbank.model.domain.Admin;

// �α����� �ʿ��� �ڵ帶�� ������ �ۼ����� ���� ���� �ڵ�� �и����� AOP�� ����
@Aspect
public class LoginCheck {
	@Pointcut("execution(public * com.itbank.controller.AdminController..main*(..))")
	public void loginCut() {

	}

	@Around("loginCut()")
	public String CheckSession(ProceedingJoinPoint joinPoint) {
		String viewName = null;
		HttpServletRequest request = null;

		// ���� ���� ���� Ȯ��
		Object[] args = joinPoint.getArgs();
		for (int i = 0; i < args.length; i++) {
			// args�� �ڷ��� �˻�
			if (args[i] instanceof HttpServletRequest) {
				request = (HttpServletRequest) args[i]; // ��û ��ü�� ���
			}
		}
		// ���ǿ� ����� �� üũ
		if (request.getSession().getAttribute("admin") == null) {
			System.out.println("�������� ���� ������Դϴ�.");
			viewName = "redirect:/admin/error/accessDeny.jsp";
		} else {
			Admin admin = (Admin) request.getSession().getAttribute("admin");
			System.out.println("�ùٸ� ������Դϴ�!" + admin.getId());
			viewName = "admin/index";
		}
		return viewName;
	}
}
