package com.dima.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dima.test.dao.CommentDao;
import com.dima.test.dao.TagDao;
import com.dima.test.dto.AlgoCategoryCount;
import com.dima.test.dto.Comment;
import com.dima.test.dto.Post;
import com.dima.test.dto.Tag;

@Service
public class TagServiceImpl implements TagService{

	@Autowired
	private TagDao dao;

	@Override
	public boolean insertTag(String tag) {
		return dao.insertTag(tag)==1;
	}

	@Override
	public int findTagNumber(String tag) {
		return dao.findTagNumber(tag);
	}

	@Override
	public boolean insertPostTag(Tag new_tag) {
		return dao.insertPostTag(new_tag)==1;
	}

	@Override
	public List<String> findTitleTag(int tagNumber) {
		return dao.findTitleTag(tagNumber);
	}

	@Override
	public List<Post> findPostByTag(String tagName) {
		return dao.findPostByTag(tagName);
	}

	@Override
	public void checkAlgoCategory(Tag tag) {
		dao.checkAlgoCategory(tag);
		
	}

	@Override
	public void modifyPostTag(AlgoCategoryCount algocount) {
		dao.modifyPostTag(algocount);
	}

	@Override
	public void insertUserTage(String userId) {
		dao.insertUserTage(userId);
	}

	@Override
	public int selectSolve(int postNumber) {
		return dao.selectSolve(postNumber);
	}

	@Override
	public List<Tag> findTagByPostNumber(int postNumber) {
		return dao.findTagByPostNumber(postNumber);
	}
}
