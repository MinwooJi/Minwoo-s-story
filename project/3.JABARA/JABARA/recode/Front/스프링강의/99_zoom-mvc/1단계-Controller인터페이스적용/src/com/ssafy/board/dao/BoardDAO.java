package com.ssafy.board.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.board.vo.Board;

public interface BoardDAO {
	List<Board> selectBoard() throws Exception;

	Board selectBoardByNo(int no) throws Exception;

	void insert(Board board) throws Exception;

	//void delete(int no)throws Exception;

	void deleteBoard(int no) throws SQLException;
}
