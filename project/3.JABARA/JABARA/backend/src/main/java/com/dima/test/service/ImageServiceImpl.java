package com.dima.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dima.test.dao.ImageUserDao;
import com.dima.test.dao.UserDao;
import com.dima.test.dto.UserImage;
@Service
public class ImageServiceImpl implements ImageService {
	@Autowired
	private ImageUserDao dao;
	@Override
	public void insertUserImage(UserImage file) {
		dao.insertUserImage(file);
	}
	@Override
	public void updateUserImage(UserImage file) {
		dao.updateUserImage(file);
	}
	
	@Override
	public String selectImagePath(String userId) {
		return dao.selectImagePath(userId);
	}

	@Override
	public String selectImageName(String userId) {
		return dao.selectImageName(userId);
	}
	
	////////////////////////////////////////////////////////////
	@Override
	public boolean insertAWSUserImage(UserImage userImage) {
		return dao.insertAWSUserImage(userImage)==1;
	}
	@Override
	public String selectAWSUserImage(String userId) {
		return dao.selectAWSUserImage(userId);
	}
	@Override
	public boolean updateAWSUserImage(UserImage userImage) {
		return dao.updateAWSUserImage(userImage)==1;
	}
	@Override
	public boolean deleteAWSUserImage(String userId) {
		return dao.deleteAWSUserImage(userId)==1;
	}

}
