package com.dima.test.dao;

import java.util.List;

import com.dima.test.dto.PostLikeTable;

public interface alrimDao {
	void insertLike(PostLikeTable postlike);

	void deleteLike(PostLikeTable postlike);

	String selectTargetUser(int postNumber);

	List<PostLikeTable> selectList(String userId);

	int selectLikePost(PostLikeTable postlike);

}
