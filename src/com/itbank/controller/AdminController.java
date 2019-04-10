package com.itbank.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.itbank.common.exception.AccountNotFoundException;
import com.itbank.common.exception.RegistFailException;
import com.itbank.model.domain.Admin;
import com.itbank.model.domain.Member;
import com.itbank.model.service.AdminService;
import com.itbank.model.service.MemberService;
import com.itbank.test.Dog;

//POJO�ϱ� �Ϲ� �޼���
@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;

	@Autowired
	private Dog dog;

	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/admin/test", method = RequestMethod.GET)
	public String test() {
		// System.out.println("���� �� �����̳� ����"+member.getName());
		// System.out.println("�ٸ� �� �����̳� ����"+dog.getName());
		return null;
	}

	// ������ �α��� ��û
	@RequestMapping(value = "/admin/login", method = RequestMethod.POST)
	public String login(Admin admin, HttpServletRequest request) {
		Admin obj = adminService.loginCheck(admin);
		// ���ǿ� ���!!!!
		request.getSession().setAttribute("admin", obj);
		return "redirect:/admin/main";
	}

	// ���� ������ ��û
	@RequestMapping(value = "/admin/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request, String msg) {
		return "admin/index";
	}

	// ȸ������ ������ ��û
	@RequestMapping(value = "/admin/member", method = RequestMethod.GET)
	public String member() {
		return "/admin/member/index";
	}

	// ȸ����� ��û (���� REST API ���°� �ƴ�!!)
	@RequestMapping(value = "/admin/member/regist", method = RequestMethod.POST)
	// Http ���信�� body�� �����͸� �Ǿ��..�׷� registMember�� ���̻� viewReoslver�� ������Ű�� �ʰ� �����͸�
	// body���Ŀ� ��Ƽ� ������.
	// .���ݺ��ʹ� jsp�� �ƴ� �� ������
	@ResponseBody // ���������� Ŭ���̾�Ʈ�� �ƴϴ�..( ����Ʈ���� Ŭ���̾�Ʈ�� �� �� �ֵ���... )
	public String registMember(Member member) {
		System.out.println(member.getId());
		System.out.println(member.getPass());
		System.out.println(member.getName());

		memberService.insert(member);

		// json �����ϱ�!!
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"result\":1");
		sb.append("}");

		return sb.toString();
	}

	@RequestMapping(value = "/admin/member/list", method = RequestMethod.GET)
	@ResponseBody // jsp�� �ؼ����� ���� �׳� �� �����ͷ� �޾�!!
	public String getList() {
		System.out.println("ȸ������� ���ϴ±���");
		List memberList = memberService.selectAll();

		// ��ü�� ���ڿ� ����ϸ� �����ϹǷ�, ���� ��ü�� ���
		// �� String���� json�� ǥ����������, json�� ���� ���
		// json�� ���� ���̺귯���� ǥ��
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		for (int i = 0; i < memberList.size(); i++) {
			Member member = (Member) memberList.get(i);
			JSONObject obj = new JSONObject();

			obj.put("member_id", member.getMember_id());
			obj.put("id", member.getId());
			obj.put("pass", member.getPass());
			obj.put("name", member.getName());

			jsonArray.add(obj);
		}
		json.put("memberList", jsonArray);

		return json.toString();
	}

	// ������ ��û
	@RequestMapping(value = "/admin/map", method = RequestMethod.GET)
	public String map() {
		return "admin/map/index";
	}

	@ExceptionHandler(AccountNotFoundException.class)
	public ModelAndView handleException(AccountNotFoundException e) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/error/errorpage");
		mav.addObject("err", e);
		return mav;
	}

	@ExceptionHandler(RegistFailException.class)
	public String registException(RegistFailException e) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"result\":0");
		sb.append("}");
		return sb.toString();
	}
}