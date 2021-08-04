package com.dima.test.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dima.test.dao.UserDao;
import com.dima.test.dto.Image;
import com.dima.test.dto.PostAndImage;
import com.dima.test.dto.User;
import com.dima.test.dto.UserImage;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao dao;
	
	@Override
	public boolean insertUser(User user) {
		return dao.insertUser(user)==1;
	}

	@Override
	public String selectEmailById(String id) {
		return dao.selectEmail(id);
	}

	@Override
	public int getUserCount(String userId) {
		return dao.useridcount(userId);
	}

	@Override
	public String getPwdById(String userId) {
		return dao.userpwdById(userId);
	}

	@Override
	public User login(User tempuser) {
		return dao.loginUser(tempuser);
	}

	@Override
	public void modifyBypassword(User user) {
		dao.changePasswordByEmail(user);
	}

	@Override
	public int getTotalCount() {
		return dao.TotalCount();
	}

	@Override
	public void modifyUser(User user) {
		dao.updateUser(user);
	}

	@Override
	public List<PostAndImage> detail(User user) {
		return dao.selectPostAndImage(user);
	}

	@Override
	public List<Image> seconddetail(User user) {
		return dao.selectFirstImage(user);
	}

	@Override
	public void deleteUser(User user) {
		dao.deleteUser(user);
	}

	@Override
	public int selectNickName(String nickname) {
		return dao.selectNickName(nickname);
	}

	@Override
	public User selectIdByNickname(String nickname) {
		return dao.selectIdByNickname(nickname);
	}

	@Override
	public String selectRankingtodayMe(String userId) {
		return dao.selectRankingtodayMe(userId);
	}

	@Override
	public String selectRankingTotalMe(String userId) {
		return dao.selectRankingTotalMe(userId);
	}

	@Override
	public User userDetailById(String userId) {
		return dao.userDetailById(userId);
	}

	@Override
	public User userDetailByNickname(String userNickname) {
		return dao.userDetailByNickname(userNickname);
	}


}
