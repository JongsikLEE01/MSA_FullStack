package com.jslee.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.jslee.spring.dto.Board;

@Repository		// 데이터 액세스 객체로 빈 등록
public class BoardDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 글 전체 조회
	 * @return boardList
	 */
	public List<Board> list() {
		String sql = " SELECT * FROM board ";
		List<Board> boardList = jdbcTemplate.query(sql, new RowMapper<Board>() {
			@Override
			public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				Board board = new Board();
				board.setNo( rs.getInt("no") );
				board.setTitle( rs.getString("title") );
                board.setWriter( rs.getString("writer") );
                board.setContent( rs.getString("content") );
                board.setRegDate( rs.getTimestamp("reg_date") );
                board.setUpdDate( rs.getTimestamp("upd_date") );
                board.setViews( rs.getInt("views") );
                
                return board;
			}
		});
		return boardList;
	}
	
	/**
	 * 게시글 조회
	 * @param no
	 * @return board
	 */
	public Board select(int no) {
		String sql = " SELECT * FROM board WHERE no = ? ";
		Board board = (Board) jdbcTemplate.queryForObject(sql, new RowMapper<Board>(){
			@Override
			public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				Board board = new Board();
				
				board.setNo( rs.getInt("no") );
				board.setTitle( rs.getString("title") );
	            board.setWriter( rs.getString("writer") );
	            board.setContent( rs.getString("content") );
	            board.setRegDate( rs.getTimestamp("reg_date") );
	            board.setUpdDate( rs.getTimestamp("upd_date") );
	            board.setViews( rs.getInt("views") );
	                
				return board;
			}
		}, no);
		return board;
	}
	
	/**
	 * 게시글 등록
	 * @param board
	 * @return result
	 */
	public int insert(Board board) {
		String sql =" INSERT INTO board( title, writer, content )"
					+ " VALUES ( ?, ?, ? ) ";
		Object[] args = new Object[] {board.getTitle(), board.getWriter(), board.getContent()};
		
		int result = jdbcTemplate.update(sql, args);
		return result;
	}
	
	/**
	 * 게시글 수정
	 * @param board
	 * @return result
	 */
	public int update(Board board) {
		String sql =" UPDATE board "
					+ " SET title = ?"
					+ " , writer = ?"
					+ " , content = ?"
					+ " , upd_date = now() "
					+ " WHERE no = ? ";
		
		String title = board.getTitle();
		String writer = board.getWriter();
		String content = board.getContent();
		int no = board.getNo();
		Object[] args = new Object[] {title, writer, content, no};
		
		int result = jdbcTemplate.update(sql, args);
		return result;
	}

	/**
	 * 게시글 삭제
	 * @param no
	 * @return result
	 */
	public int delete(int no) {
		String sql =" DELETE FROM board "
					+ " WHERE no = ? ";
		Object[] args = new Object[] {no};
		
		int result = jdbcTemplate.update(sql, args);
		return result;
	}
	
}