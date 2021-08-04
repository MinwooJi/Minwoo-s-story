package com.dima.test.dao;

import com.dima.test.dto.Post;
import com.dima.test.dto.PostImage;

public interface ImagePostDao {
	int insertPostImage(PostImage postimage);
	String selectPostImage(int postNumber);
	int updatePostImage(PostImage postimage);
	int deletePostImage(int postNumber);
}
