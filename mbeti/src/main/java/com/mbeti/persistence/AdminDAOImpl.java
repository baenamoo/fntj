package com.mbeti.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mbeti.domain.MemberVO;

@Repository
public class AdminDAOImpl implements AdminDAO{

	@Inject SqlSession sql;
	
	// 회원리스트
	@Override
	public List<MemberVO> list() throws Exception {
		return sql.selectList("adminMapper.list");
	}
	
	//회원 상세 조회
	@Override
	public MemberVO retrieve(String userID) throws Exception {
			
		return sql.selectOne("adminMapper.retrieve", userID);
	}

	// 회원정보 수정
	@Override
	public void memberUpdate(MemberVO memberVO) throws Exception {
		sql.update("adminMapper.memberUpdate", memberVO);
	}
	
	// 회원 탈퇴
	@Override
	public void delete(String userID) throws Exception {
		sql.delete("adminMapper.memberDelete", userID);
	}
	
	
	
}
