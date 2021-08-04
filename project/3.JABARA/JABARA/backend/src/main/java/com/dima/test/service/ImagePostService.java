package com.dima.test.service;

import com.dima.test.dto.Post;
import com.dima.test.dto.PostImage;

public interface ImagePostService {
	public boolean insertPostImage(PostImage postimage);
	public String selectPostImage(int postNumber);
	public boolean updatePostImage(PostImage postimage);
	public boolean deletePostImage(int postNumber);
}
