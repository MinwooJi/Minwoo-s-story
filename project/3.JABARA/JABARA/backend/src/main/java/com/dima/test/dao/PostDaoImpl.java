package com.dima.test.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dima.test.dto.Post;
import com.dima.test.dto.RankUser;

@Repository
public class PostDaoImpl implements PostDao {
	private static final String NS = "com.dima.test.dao.PostDao.";

	@Autowired
	private SqlSession sqlSession;
	
	public List<Post> searchPost(){
		List<Post> result = sqlSession.selectList(NS+"searchPost");
		List<Post> list = new ArrayList<>();
		int i = 0;
		for(Post post: result) {
			post = result.get(i);
			list.add(post);
			i++;
		}
		return list;
//		// DTO와 DB가 일치하지 않으므로 HashMap List 사용
//		List<HashMap<String, Object>> result = sqlSession.selectList(NS+"searchPost");
//
//		// 가져온 Post 객체들을 저장할 새로운 배열 선언
//		List<Post> list = new ArrayList<Post>();
//
//		// 받아온 리스트를 HashMap에 차례로 넣어준다.
//		for(HashMap<String, Object> hash : result) {
//			// hash의 키값을 가져오기
//			Set key = hash.keySet();
//			// Iterator 란?
//			// Iterator는 자바의 컬렉션 프레임웍에서 컬렉션에 저장되어 있는 요소들을 읽어오는 방법을 표준화 하였는데 그 중 하나가 Iterator
//			// Iterator에 key를 담는다.
//			Iterator iterator = key.iterator();
//
//			Post post = new Post();
//			post.setPostAlgoTitle(hash.get(iterator.next()).toString());
//			post.setPostCommentCount(Integer.parseInt(hash.get(iterator.next()).toString()));
//			post.setPostSnsContent(hash.get(iterator.next()).toString());
//			post.setPostNumber(Integer.parseInt(hash.get(iterator.next()).toString()));
//			post.setUserId(hash.get(iterator.next()).toString());
//			post.setPostDate((java.sql.Timestamp)hash.get(iterator.next()));
//			post.setPostLike(Integer.parseInt(hash.get(iterator.next()).toString()));
//			post.setPostCategory(hash.get(iterator.next()).toString());
//			post.setIsSolved(Integer.parseInt(hash.get(iterator.next()).toString()));
//			list.add(post);
//			// hasNext를 통해 다음 KEY값 세트로 넘어간다.
//			iterator.hasNext();
//		}
	}
	@Override
	public Post searchPostByNo(int postNumber) {
		Post post =  sqlSession.selectOne(NS+"searchPostByNo",postNumber);
		return post;
	}
	
	@Override
	public List<Post> searchPostByFollowing(String userId) {
		List<Post> result = sqlSession.selectList(NS+"searchPostByFollowing", userId);
		List<Post> list = new ArrayList<>();
		int i = 0;
		for(Post post: result) {
			post = result.get(i);
			list.add(post);
			i++;
		}
		return list;
	}
	@Override
	public int insertSns(Post post) {
		return sqlSession.insert(NS+"insertSns", post);
	}
	@Override
	public int insertCode(Post post) {
		return sqlSession.insert(NS+"insertCode", post);
	}
	@Override
	public List<Post> searchPostById(String userId) {
		List<Post> result = sqlSession.selectList(NS+"searchPostById", userId);
		List<Post> list = new ArrayList<>();
		int i = 0;
		for(Post post: result) {
			post = result.get(i);
			list.add(post);
			i++;
		}
		return list;
	}
	@Override
	public int updateSns(Post post) {
		return sqlSession.insert(NS+"updateSns", post);
	}
	@Override
	public int updateCode(Post post) {
		return sqlSession.insert(NS+"updateCode", post);
	}
	@Override
	public int deletePost(int postNumber) {
		return sqlSession.delete(NS+"deletePost",postNumber);
	}
	@Override
	public List<RankUser> selectRankingtoday() {
		List<RankUser> temp = sqlSession.selectList(NS+"selectRankUserToday");
		System.out.println(temp.size());
		return temp;
	}
	@Override
	public List<RankUser> selectRankingTotal() {
		List<RankUser> temp = sqlSession.selectList(NS+"selectRankingTotal");
		System.out.println(temp.size());
		return temp;
	}
	
	@Override
	public void likePost(int postNumber) {
		sqlSession.update(NS+"likePost",postNumber);
	}
	
	@Override
	public void unlikePost(int postNumber) {
		sqlSession.update(NS+"unlikePost",postNumber);
	}
	@Override
	public int searchlikePost(int postNumber) {
		return sqlSession.selectOne(NS+"unlikePost",postNumber);
	}
	@Override
	public List<String> searchAlgoSolved(String userId) {
		List<String> result = sqlSession.selectList(NS+"searchAlgoSolved",userId);
		if(result.isEmpty()) {
			result.add("푼 문제가 없습니다.");
			return result;
		}
		List<String> transResult = new ArrayList<String>();
		int i=0;
		String match="[^\\uAC00-\\uD7A3xfe0-9a-zA-Z\\\\s]";
		while(true) {
			String title = result.get(i).toString();
			int count=0;
			for(int j=0; j<title.length(); j++) {
				if(title.charAt(j)=='_') count++;
			}
			if(count==2) {
				for(int j=0; j<title.length(); j++) {
					if(title.charAt(j)=='_') {
						title = title.replaceFirst(match, "");
						break;
					}
				}
			}
			title = title.replaceFirst(match, "(");
			title = title+")";
			transResult.add(title);
			i++;
			if(result.size()==i) break;
		}
		return transResult;
	}
	
	@Override
	public List<String> searchAlgoUnSolved(String userId) {
		List<String> result = sqlSession.selectList(NS+"searchAlgoUnSolved",userId);
		if(result.isEmpty()) {
			result.add("못 푼 문제가 없습니다.");
			return result;
		}
		List<String> transResult = new ArrayList<String>();
		int i=0;
		String match="[^\\uAC00-\\uD7A3xfe0-9a-zA-Z\\\\s]";
		while(true) {
			String title = result.get(i).toString();
			int count=0;
			for(int j=0; j<title.length(); j++) {
				if(title.charAt(j)=='_') count++;
			}
			if(count==2) {
				for(int j=0; j<title.length(); j++) {
					if(title.charAt(j)=='_') {
						title = title.replaceFirst(match, "");
						break;
					}
				}
			}
			title = title.replaceFirst(match, "(");
			title = title+")";
			transResult.add(title);
			i++;
			if(result.size()==i) break;
		}
		return transResult;
	}
	@Override
	public int selectPostNumber(Post post) {
		return sqlSession.selectOne(NS+"selectPostNumber",post);
	}
	@Override
	public List<Post> searchAlgoCalendarChart(String userId) {
		return sqlSession.selectList(NS+"searchAlgoCalendarChart",userId);
	}
	@Override
	public int insertStarPost(Post post) {
		return sqlSession.insert(NS+"insertStarPost",post);
	}
	@Override
	public int deleteStarPost(Post post) {
		return sqlSession.delete(NS+"deleteStarPost",post);
	}
	@Override
	public int checkStarPost(Post post) {
		return sqlSession.selectOne(NS+"checkStarPost",post);
	}
	@Override
	public List<Post> selectStarPost(String userId) {
		return sqlSession.selectList(NS+"selectStarPost",userId);
	}
	@Override
	public int myRankingTotal(String userId) {
		return sqlSession.selectOne(NS+"myRankingTotal",userId);
	}

	@Override
	   public List<Post> selectPostInfLoading(int start) {
	      List<Post> result = sqlSession.selectList(NS+"selectPostInfLoading",start);
	      // TODO Auto-generated method stub
	      return result;
	   }

}
