package com.crawler.biz.board.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crawler.biz.board.BoardVO;

@Repository
public class BoardDAOMybatis {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertBoard(BoardVO vo) {
		System.out.println("===> Mybatis로 insertBoard() 등록 기능 처리");
		mybatis.insert("BoardDAO.insertBoard", vo);
	}
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> Mybatis로 deleteBoard() 등록 기능 처리");
		mybatis.delete("BoardDAO.deleteBoard", vo);
	}
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> Mybatis로 getBHIList() 조회 기능 처리");
		return mybatis.selectOne("BoardDAO.getBoard", vo);
	}
	public List<BoardVO> getBoardList(BoardVO vo){
		System.out.println("===> Mybatis로 getBoardList() 조회 기능 처리");
		return mybatis.selectList("BoardDAO.getBoardList", vo);
	}
	public Integer boardCnt(BoardVO vo) {
		System.out.println("===> Mybatis로 boardCnt() 조회 기능 처리");
		return mybatis.selectOne("BoardDAO.boardCnt", vo);
	}
}
