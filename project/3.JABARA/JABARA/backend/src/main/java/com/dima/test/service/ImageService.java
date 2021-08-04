package com.dima.test.service;

import com.dima.test.dto.UserImage;

public interface ImageService {
	public void insertUserImage(UserImage file);
	public void updateUserImage(UserImage file);
	public String selectImagePath(String userId);
	public String selectImageName(String userId);
	//////////////////////////////////////////////////
	public boolean insertAWSUserImage(UserImage userImage);
	public String selectAWSUserImage(String userId);
	public boolean updateAWSUserImage(UserImage userImage);
	public boolean deleteAWSUserImage(String userId);
}
