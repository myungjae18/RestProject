package com.itbank.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.itbank.common.exception.RegistFailException;
import com.itbank.model.domain.Member;
import com.itbank.model.repository.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	@Qualifier("mybatisMemberDAO")
	private MemberDAO memberDAO;

	@Override
	public List selectAll() {
		return memberDAO.selectAll();
	}

	@Override
	public Member select(int member_id) {

		return null;
	}

	@Override
	public Member loginCheck(Member member) {

		return null;
	}

	@Override
	public void insert(Member member) throws RegistFailException {
		int result = memberDAO.insert(member);

		if (result == 0) {
			throw new RegistFailException("등록에 실패했습니다..");
		}
	}

	@Override
	public void update(Member member) {

	}

	@Override
	public void delete(int member_id) {

	}

}
