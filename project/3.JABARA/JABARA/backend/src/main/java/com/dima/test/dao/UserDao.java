package com.dima.test.dao;

import java.util.List;

import com.dima.test.dto.Image;
import com.dima.test.dto.PostAndImage;
import com.dima.test.dto.User;
import com.dima.test.dto.UserImage;

public interface UserDao {
	int insertUser(User user);
	String selectEmail(String id);
	int useridcount(String userId);
	String userpwdById(String userId);
	User loginUser(User tempuser);
	void changePasswordByEmail(User user);
	int TotalCount();
	void updateUser(User user);
	List<PostAndImage> selectPostAndImage(User user);
	List<Image> selectFirstImage(User user);
	void deleteUser(User user);
	int selectNickName(String nickname);
	User selectIdByNickname(String nickname);
	String selectRankingtodayMe(String userId);
	String selectRankingTotalMe(String userId);
	User userDetailById(String userId);
	User userDetailByNickname(String userNickname);
}
