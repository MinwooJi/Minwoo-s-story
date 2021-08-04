package com.dima.test.dao;

import java.util.List;

import com.dima.test.dto.Follow;

public interface FollowDao {
	public int insertFollow(Follow follow);
	public int deleteFollow(Follow follow);
	public List<Follow> selectFollowById(String userId);
	public List<Follow> selectFollowingById(String followingId);
	public int selectFollowCountById(String userId);
	public int selectFollowingCountById(String userId);
	public int IsFollow(Follow follow);
}
