package com.mbeti.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mbeti.domain.MemberVO;
import com.mbeti.persistence.MemberDAO;

@Service
public class MemberServiceImpl  implements MemberService {
	
	@Inject MemberDAO dao;
	
	//회원가입
	@Override
	public void register(MemberVO vo) throws Exception {
		
		dao.register(vo);
	}

	//로그인
	@Override
	public MemberVO login(MemberVO vo) throws Exception {
		return dao.login(vo);
	}
	
	//회원정보수정
	@Override
	public void memberUpdate(MemberVO vo) throws Exception {
		dao.memberUpdate(vo);
		
	}
	
	//회원탈퇴
	@Override
	public void memberDelete(MemberVO vo) throws Exception {
		dao.memberDelete(vo);
	}
	
	// 패스워드 체크
	@Override
	public int passChk(MemberVO vo) throws Exception {
		int result = dao.passChk(vo);
		return result;
	}
	
	// 아이디 중복 체크
	@Override
	public int idChk(MemberVO vo) throws Exception {
		int result = dao.idChk(vo);
		return result;
	}
	
	// 닉네임 중복 체크
	@Override
	public int nameChk(MemberVO vo) throws Exception {
		int result = dao.nameChk(vo);
		return result;
	}
	
}
