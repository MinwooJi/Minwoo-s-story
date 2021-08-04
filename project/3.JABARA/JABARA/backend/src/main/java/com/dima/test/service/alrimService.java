package com.dima.test.service;

import java.util.List;

import com.dima.test.dto.PostLikeTable;

public interface alrimService {
	void insertLike(PostLikeTable postlike);

	void deleteLike(PostLikeTable postlike);

	String selectTargetUser(int postNumber);

	List<PostLikeTable> selectList(String userId) throws Exception;

	int selectLikePost(PostLikeTable postlike);


}
