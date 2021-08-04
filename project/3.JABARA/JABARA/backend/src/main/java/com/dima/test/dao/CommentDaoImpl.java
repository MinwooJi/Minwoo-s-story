package com.dima.test.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dima.test.dto.Comment;
import com.dima.test.dto.PostLikeTable;

@Repository
public class CommentDaoImpl implements CommentDao {
	private static final String NS = "com.dima.test.dao.CommentDao.";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Comment> searchComment(int postNumber) {
		List<Comment> result = sqlSession.selectList(NS+"searchComment",postNumber);
		return result;
	}

	@Override
	public int insertComment(Comment comment) {
		return sqlSession.insert(NS+"insertComment",comment);
	}

	@Override
	public int updateComment(Comment comment) {
		return sqlSession.update(NS+"updateComment",comment);
	}

	@Override
	public int deleteComment(Comment comment) {
		return sqlSession.delete(NS+"deleteComment",comment);
	}

	@Override
	public int increaseComment(int postNumber) {
		return sqlSession.update(NS+"increaseComment",postNumber);
	}

	@Override
	public int selectCommentCount(int postNumber) {
		return sqlSession.selectOne(NS+"selectCommentCount",postNumber);
	}

	@Override
	public List<Comment> selectList(String userId) {
		 List<HashMap<String,Object>>result = sqlSession.selectList(NS+"selectList",userId);
		 List<Comment> list = new ArrayList<Comment>();
		 for (int i = 0; i < result.size(); i++) {
			 Comment temp = new Comment();
				for (Entry<String, Object> elem : result.get(i).entrySet()) {
					if (elem.getKey().equals("TARGETUSER_ID")) {
						System.out.println("TARGETUSER_ID: "+elem.getValue());
						temp.setTargetuserId((String)elem.getValue());
					}
					if (elem.getKey().equals("COMMENT_DATE")) {
						System.out.println("COMMENT_DATE: "+elem.getValue());
						temp.setCommentDate((Timestamp)elem.getValue());
					}
					if (elem.getKey().equals("COMMENT_NUMBER")) {
						System.out.println("COMMENT_NUMBER: "+elem.getValue());
						temp.setPostNumber((int)elem.getValue());
					}
					if(elem.getKey().equals("USER_ID")){
						System.out.println("USER_ID: "+elem.getValue());
						temp.setUserId((String)elem.getValue());
					}
					if(elem.getKey().equals("COMMENT_CONTENT")) {
						System.out.println("COMMENT_CONTENT: "+elem.getValue());
						temp.setCommentContent((String)elem.getValue());
					}
					
					list.add(temp);
				}
		 }
		 return list;
	}
}
