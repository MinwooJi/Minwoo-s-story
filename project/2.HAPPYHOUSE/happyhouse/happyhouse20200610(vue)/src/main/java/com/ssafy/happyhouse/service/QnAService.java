package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.QnA;

public interface QnAService {

	List<QnA> retrieveQnA();

	QnA detailQnA(int qnaNo);

	boolean writeQnA(QnA qna);

	boolean updateQnA(QnA qna);

	boolean deleteQnA(int qnaNo);
}
