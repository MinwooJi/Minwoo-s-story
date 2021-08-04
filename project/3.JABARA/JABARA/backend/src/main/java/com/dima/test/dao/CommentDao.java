package com.dima.test.dao;

import java.util.List;

import com.dima.test.dto.Comment;

public interface CommentDao {
	List<Comment> searchComment(int postNumber);
	int insertComment(Comment comment);
	int updateComment(Comment comment);
	int deleteComment(Comment comment);
	int increaseComment(int postNumber);
	int selectCommentCount(int postNumber);
	List<Comment> selectList(String userId);
}
