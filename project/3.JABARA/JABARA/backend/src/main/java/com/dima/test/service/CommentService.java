package com.dima.test.service;

import java.util.List;

import com.dima.test.dto.Comment;

public interface CommentService {
	public List<Comment> searchComment(int postNumber);
	public boolean insertComment(Comment comment);
	public boolean updateComment(Comment comment);
	public boolean deleteComment(Comment comment);
	public boolean increaseComment(int postNumber);
	public int selectCommentCount(int postNumber);
	public List<Comment> selectList(String userId);
}
