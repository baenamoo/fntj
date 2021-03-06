package com.mbeti.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mbeti.domain.BoardVO;
import com.mbeti.domain.Criteria;
import com.mbeti.domain.FileVO;
import com.mbeti.domain.SearchCriteria;


@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession sqlSession;
	
	// 게시글 작성
	@Override
	public void write(BoardVO boardVO) throws Exception {
		sqlSession.insert("boardMapper.insert", boardVO);
		
	}
	
	// 게시물 목록 조회
	@Override
	public List<BoardVO> list(SearchCriteria scri) throws Exception {
	
		return sqlSession.selectList("boardMapper.listPage", scri);

	}
	
	// 게시물 페이징
	@Override
	public int listCount(SearchCriteria scri) throws Exception {
		return sqlSession.selectOne("boardMapper.listCount", scri);
	}
	
	
	// 게시물 조회
	@Override
	public BoardVO read(int bno) throws Exception {
			
		return sqlSession.selectOne("boardMapper.read", bno);
	}
	
	// 게시물 수정
	@Override
	public void update(BoardVO boardVO) throws Exception {
		
		sqlSession.update("boardMapper.update", boardVO);
	}

	// 게시물 삭제
	@Override
	public void delete(int bno) throws Exception {
		
		sqlSession.delete("boardMapper.delete", bno);
	}

	// 첨부파일 업로드
	@Override
	public void insertFile(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert("boardMapper.insertFile", map);
	}
	
	// 첨부파일 조회
	@Override
	public List<Map<String, Object>> selectFileList(int bno) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("boardMapper.selectFileList", bno);
	}
	
	// 첨부파일 다운로드
	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("boardMapper.selectFileInfo", map);
	}
	
	@Override
	public void updateFile(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		
		sqlSession.update("boardMapper.updateFile", map);
	}
	
	@Override
	public void boardHit(int bno) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("boardMapper.boardHit", bno);
	}
	
	//댓글수
	@Override public void updateReplyCnt(Integer bno, int amount) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		paramMap.put("bno", bno); paramMap.put("amount", amount); 
		sqlSession.update("boardMapper.updateReplyCnt",paramMap); 
		}
	
	//파일목록조회
	@Override
	public List<FileVO> fileList() throws Exception {
		return sqlSession.selectList("boardMapper.fileList");
	}

	
	
	
}