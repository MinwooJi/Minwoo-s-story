package com.dima.test.dao;

import java.util.List;
import com.dima.test.dto.Post;
import com.dima.test.dto.RankUser;
import com.dima.test.dto.User;

public interface PostDao {
	List<Post> searchPost();
	Post searchPostByNo(int postNumber);
	List<Post> searchPostById(String userId);
	List<Post> searchPostByFollowing(String userId);
	int insertSns(Post post);
	int insertCode(Post post);
	int updateSns(Post post);
	int updateCode(Post post);
	int deletePost(int postNumber);
	List<RankUser> selectRankingtoday();
	List<RankUser> selectRankingTotal();
	void likePost(int postNumber);
	void unlikePost(int postNumber);
	int searchlikePost(int postNumber);
	List<String> searchAlgoSolved(String userId);
	List<String> searchAlgoUnSolved(String userId);
	int selectPostNumber(Post post);
	List<Post> searchAlgoCalendarChart(String userId);
	int insertStarPost(Post post);
	int deleteStarPost(Post post);
	int checkStarPost(Post post);
	List<Post> selectStarPost(String userId);
	int myRankingTotal(String userId);
	List<Post> selectPostInfLoading(int start);
}
