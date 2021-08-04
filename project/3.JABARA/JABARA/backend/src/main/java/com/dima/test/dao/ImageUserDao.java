package com.dima.test.dao;

import com.dima.test.dto.UserImage;

public interface ImageUserDao {
	void insertUserImage(UserImage file);
	void updateUserImage(UserImage file);
	String selectImagePath(String userId);
	String selectImageName(String userId);

	/////////////////////////////////////////////////
	int insertAWSUserImage(UserImage userImage);
	String selectAWSUserImage(String userId);
	int updateAWSUserImage(UserImage userImage);
	int deleteAWSUserImage(String userId);
}
