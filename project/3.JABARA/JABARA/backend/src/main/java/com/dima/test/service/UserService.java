package com.dima.test.service;

import java.util.List;

import com.dima.test.dto.Image;
import com.dima.test.dto.PostAndImage;
import com.dima.test.dto.User;
import com.dima.test.dto.UserImage;

public interface UserService{
	public boolean insertUser(User user);
	public String selectEmailById(String id);
	public int getUserCount(String userId);
	public String getPwdById(String userId);
	public User login(User tempuser);
	public void modifyBypassword(User user);
	public int getTotalCount();
	public void modifyUser(User user);
	public List<PostAndImage> detail(User user);
	public List<Image> seconddetail(User user);
	public void deleteUser(User user);
	public int selectNickName(String nickname);
	public User selectIdByNickname(String nickname);
	public String selectRankingtodayMe(String userId);
	public String selectRankingTotalMe(String userId);
	public User userDetailById(String userId);
	public User userDetailByNickname(String userNickname);
}
