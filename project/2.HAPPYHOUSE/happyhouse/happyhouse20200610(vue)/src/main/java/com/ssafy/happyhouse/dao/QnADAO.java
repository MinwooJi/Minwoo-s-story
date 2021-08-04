package com.ssafy.happyhouse.dao;

import java.util.List;

import com.ssafy.happyhouse.dto.QnA;

public interface QnADAO {

	List<QnA> selectQnA();
	
	QnA selectQnAByNo(int qnaNo);

	boolean insertQnA(QnA qna);

	boolean updateQnA(QnA qna);

	boolean deleteQnA(int qnaNo);
}
