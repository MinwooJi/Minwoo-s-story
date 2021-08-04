package com.dima.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dima.test.dao.CommentDao;
import com.dima.test.dto.Comment;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentDao dao;
	
	@Override
	public List<Comment> searchComment(int postNumber) {
		return dao.searchComment(postNumber);
	}

	@Override
	public boolean insertComment(Comment comment) {
		return dao.insertComment(comment)==1;
	}

	@Override
	public boolean updateComment(Comment comment) {
		return dao.updateComment(comment)==1;
	}

	@Override
	public boolean deleteComment(Comment comment) {
		return dao.deleteComment(comment)==1;
	}

	@Override
	public boolean increaseComment(int postNumber) {
		return dao.increaseComment(postNumber)==1;
	}

	@Override
	public int selectCommentCount(int postNumber) {
		return dao.selectCommentCount(postNumber);
}

	@Override
	public List<Comment> selectList(String userId) {
		return dao.selectList(userId);
	}
}
