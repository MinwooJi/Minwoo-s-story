package com.dima.test.service;

import java.util.List;

import com.dima.test.dto.AlgoCategoryCount;
import com.dima.test.dto.Post;
import com.dima.test.dto.Tag;

public interface TagService {
	public boolean insertTag(String tag);
	public int findTagNumber(String tag);
	public boolean insertPostTag(Tag new_tag);
	public List<String> findTitleTag(int tagNumber);
	public List<Post> findPostByTag(String tagName);
	public void checkAlgoCategory(Tag tag);
	public void modifyPostTag(AlgoCategoryCount algocount);
	public void insertUserTage(String userId);
	public int selectSolve(int postNumber);
	public List<Tag> findTagByPostNumber(int postNumber);
}
