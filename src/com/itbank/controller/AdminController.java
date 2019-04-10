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

//POJO니까 일반 메서드
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
		// System.out.println("나의 웹 컨테이너 영역"+member.getName());
		// System.out.println("다른 웹 컨테이너 영역"+dog.getName());
		return null;
	}

	// 관리자 로그인 요청
	@RequestMapping(value = "/admin/login", method = RequestMethod.POST)
	public String login(Admin admin, HttpServletRequest request) {
		Admin obj = adminService.loginCheck(admin);
		// 세션에 담기!!!!
		request.getSession().setAttribute("admin", obj);
		return "redirect:/admin/main";
	}

	// 메인 페이지 요청
	@RequestMapping(value = "/admin/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request, String msg) {
		return "admin/index";
	}

	// 회원정보 페이지 요청
	@RequestMapping(value = "/admin/member", method = RequestMethod.GET)
	public String member() {
		return "/admin/member/index";
	}

	// 회원등록 요청 (아직 REST API 쓰는거 아님!!)
	@RequestMapping(value = "/admin/member/regist", method = RequestMethod.POST)
	// Http 응답에서 body에 데이터를 실어라..그럼 registMember는 더이상 viewReoslver를 관여시키지 않고 데이터를
	// body형식에 담아서 보낸다.
	// .지금부터는 jsp가 아닌 날 데이터
	@ResponseBody // 브라우저만이 클라이언트는 아니다..( 스마트폰도 클라이언트가 될 수 있듯이... )
	public String registMember(Member member) {
		System.out.println(member.getId());
		System.out.println(member.getPass());
		System.out.println(member.getName());

		memberService.insert(member);

		// json 구성하기!!
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"result\":1");
		sb.append("}");

		return sb.toString();
	}

	@RequestMapping(value = "/admin/member/list", method = RequestMethod.GET)
	@ResponseBody // jsp로 해석하지 말고 그냥 날 데이터로 받아!!
	public String getList() {
		System.out.println("회원목록을 원하는군요");
		List memberList = memberService.selectAll();

		// 객체를 문자열 취급하면 불편하므로, 실제 객체로 취급
		// 즉 String으로 json을 표현하지말고, json을 직접 사용
		// json을 구글 라이브러리로 표현
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

	// 맵정보 요청
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