package com.dima.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dima.test.dao.ImagePostDao;
import com.dima.test.dto.Post;
import com.dima.test.dto.PostImage;
@Service
public class ImagePostServiceImpl implements ImagePostService {
	@Autowired
	private ImagePostDao dao;
	
	@Override
	public boolean insertPostImage(PostImage postimage) {
		return dao.insertPostImage(postimage)==1;
	}
	@Override
	public String selectPostImage(int postNumber) {
		return dao.selectPostImage(postNumber);
	}
	@Override
	public boolean updatePostImage(PostImage postimage) {
		return dao.updatePostImage(postimage)==1;
	}
	@Override
	public boolean deletePostImage(int postNumber) {
		return dao.deletePostImage(postNumber)==1;
	}

}
