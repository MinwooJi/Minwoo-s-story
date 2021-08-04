package com.dima.test.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dima.test.dao.FollowDao;
import com.dima.test.dao.UserDao;
import com.dima.test.dto.Follow;
import com.dima.test.dto.Image;
import com.dima.test.dto.PostAndImage;
import com.dima.test.dto.User;
import com.dima.test.dto.UserImage;

@Service
public class FollowServiceImpl implements FollowService{
	@Autowired
	private FollowDao dao;

	@Override
	public boolean insertFollow(Follow follow) {
		return dao.insertFollow(follow)==1;
	}

	@Override
	public boolean deleteFollow(Follow follow) {
		return dao.deleteFollow(follow)==1;
	}

	@Override
	public List<Follow> selectFollowById(String userId) {
		return dao.selectFollowById(userId);
	}

	@Override
	public List<Follow> selectFollowingById(String followingId) {
		return dao.selectFollowingById(followingId);
	}

	@Override
	public int selectFollowCountById(String userId) {
		return dao.selectFollowCountById(userId);
	}

	@Override
	public int selectFollowingCountById(String userId) {
		return dao.selectFollowingCountById(userId);
	}

	@Override
	public int IsFollow(Follow follow) {
		return dao.IsFollow(follow);
	}
	

}
