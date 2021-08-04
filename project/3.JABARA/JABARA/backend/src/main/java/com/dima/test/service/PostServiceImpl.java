package com.dima.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dima.test.dao.PostDao;
import com.dima.test.dto.Post;
import com.dima.test.dto.RankUser;
import com.dima.test.dto.User;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDao dao;
	
	public List<Post> searchPost(){
		return dao.searchPost();
	}
	@Override
	   public List<Post> selectPostInfLoading(int start) {
	      // TODO Auto-generated method stub
	      return dao.selectPostInfLoading(start);
	   }
	@Override
	public Post searchPostByNo(int postNumber) {
		return dao.searchPostByNo(postNumber);
	}

	@Override
	public boolean insertSns(Post post) {
		return dao.insertSns(post)==1;
	}

	@Override
	public boolean insertCode(Post post) {
		return dao.insertCode(post)==1;
	}

	@Override
	public List<Post> searchPostById(String userId) {
		return dao.searchPostById(userId);
	}

	@Override
	public List<Post> searchPostByFollowing(String userId) {
		return dao.searchPostByFollowing(userId);
	}
	
	@Override
	public boolean updateSns(Post post) {
		return dao.updateSns(post)==1;
	}

	@Override
	public boolean updateCode(Post post) {
		return dao.updateCode(post)==1;
	}

	@Override
	public boolean deletePost(int postNumber) {
		return dao.deletePost(postNumber)==1;
	}

	@Override
	public List<RankUser> selectRankingtoday() {
		return dao.selectRankingtoday();
	}

	@Override
	public List<RankUser> selectRankingTotal() {
		return dao.selectRankingTotal();
	}

	@Override
	public void likePost(int postNumber) {
		dao.likePost(postNumber);
	}

	@Override
	public void unlikePost(int postNumber) {
		dao.unlikePost(postNumber);
	}

	@Override
	public int searchlikePost(int postNumber) {
		return dao.searchlikePost(postNumber);
	}
	@Override
	public List<String> searchAlgoSolved(String userId) {
		return dao.searchAlgoSolved(userId);
	}
	
	@Override
	public List<String> searchAlgoUnSolved(String userId) {
		return dao.searchAlgoUnSolved(userId);
	}

	@Override
	public int selectPostNumber(Post post) {
		return dao.selectPostNumber(post);
	}

	@Override
	public List<Post> searchAlgoCalendarChart(String userId) {
		return dao.searchAlgoCalendarChart(userId);
	}

	@Override
	public boolean insertStarPost(Post post) {
		return dao.insertStarPost(post)==1;
	}
	
	@Override
	public boolean checkStarPost(Post post) {
		return dao.checkStarPost(post)>=1;
	}

	@Override
	public boolean deleteStarPost(Post post) {
		return dao.deleteStarPost(post)==1;
	}

	@Override
	public List<Post> selectStarPost(String userId) {
		return dao.selectStarPost(userId);
	}

	@Override
	public int myRankingTotal(String userId) {
		return dao.myRankingTotal(userId);
	}


}
