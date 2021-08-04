package com.ssafy.board.service;

import java.util.List;

import com.ssafy.board.vo.Board;

public interface BoardService {
	List<Board> list() throws Exception;

	Board detail(int no) throws Exception;
	
	void insert(Board board)throws Exception;

	//void remove(int no)throws Exception;

	void delete(int no)throws Exception;
}
