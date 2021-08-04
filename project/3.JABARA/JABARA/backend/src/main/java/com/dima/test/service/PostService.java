package com.dima.test.service;

import java.util.List;

import com.dima.test.dto.Post;
import com.dima.test.dto.RankUser;
import com.dima.test.dto.User;

public interface PostService {
	public List<Post> searchPost();
	public Post searchPostByNo(int postNumber);
	public boolean insertSns(Post post);
	public boolean insertCode(Post post);
	public List<Post> searchPostById(String userId);
	public List<Post> searchPostByFollowing(String userId);
	public boolean updateSns(Post post);
	public boolean updateCode(Post post);
	public boolean deletePost(int postNumber);
	public List<RankUser> selectRankingtoday();
	public List<RankUser> selectRankingTotal();
	public void likePost(int postNumber);
	public void unlikePost(int postNumber);
	public int searchlikePost(int postNumber);
	public List<String> searchAlgoSolved(String userId);
	public List<String> searchAlgoUnSolved(String userId);
	public int selectPostNumber(Post post);
	public List<Post> searchAlgoCalendarChart(String userId);
	public boolean insertStarPost(Post post);
	public boolean deleteStarPost(Post post);
	public boolean checkStarPost(Post post);
	public List<Post> selectStarPost(String userId);
	int myRankingTotal(String userId);
	public List<Post> selectPostInfLoading(int start);
}