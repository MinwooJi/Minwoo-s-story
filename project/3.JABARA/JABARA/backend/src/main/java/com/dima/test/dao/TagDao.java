package com.dima.test.dao;

import java.util.List;

import com.dima.test.dto.AlgoCategoryCount;
import com.dima.test.dto.Post;
import com.dima.test.dto.Tag;

public interface TagDao {
	int insertTag(String tag);
	int findTagNumber(String tag);
	int insertPostTag(Tag new_tag);
	List<String> findTitleTag(int postNumber);
	List<Post> findPostByTag(String tagName);
	void checkAlgoCategory(Tag tag);
	void modifyPostTag(AlgoCategoryCount algocount);
	void insertUserTage(String userId);
	int selectSolve(int postNumber);
	List<Tag> findTagByPostNumber(int postNumber);
}
