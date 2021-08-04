package com.dima.test.service;

import java.util.List;

import com.dima.test.dto.Follow;

public interface FollowService{
	public boolean insertFollow(Follow follow);
	public boolean deleteFollow(Follow follow);
	public List<Follow> selectFollowById(String userId);
	public List<Follow> selectFollowingById(String followingId);
	public int selectFollowCountById(String userId);
	public int selectFollowingCountById(String userId);
	public int IsFollow(Follow follow);
}
