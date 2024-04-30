package com.jslee.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jslee.spring.dao.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService{
	
	private BoardDAO boardDAO;
	
	/*
	 * 생성자 주입
	 * @param BoardDAO
	 */
	@Autowired
	public BoardServiceImpl(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	@Override
	public void test() {
		boardDAO.test();
	}
	
	@Autowired
	public void setBoardDAO(BoardDAO boardDAO) {
		
	}
}