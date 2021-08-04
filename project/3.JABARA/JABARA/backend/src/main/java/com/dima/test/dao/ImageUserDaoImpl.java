package com.dima.test.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dima.test.dto.UserImage;

@Repository
public class ImageUserDaoImpl implements ImageUserDao {
	private static final String NS = "com.dima.test.dao.ImageUserDao.";
	@Autowired
	private SqlSession sqlSession;
	@Override
	public void insertUserImage(UserImage file) {
		sqlSession.insert(NS+"insertUserImage", file);
	}
	@Override
	public void updateUserImage(UserImage file) {
		sqlSession.update(NS+"updateUserImage", file);
	}


	@Override
	public String selectImagePath(String userId) {
		return sqlSession.selectOne(NS+"selectImagePath",userId);
	}

	@Override
	public String selectImageName(String userId) {
		return sqlSession.selectOne(NS+"selectImageName",userId);
	}
	
///////////////////////////////////////////////////
///////////////////////////////////////////////////
@Override
public int insertAWSUserImage(UserImage userImage) {
return sqlSession.insert(NS+"insertAWSUserImage", userImage);
}

@Override
public String selectAWSUserImage(String userId) {
return sqlSession.selectOne(NS+"selectAWSUserImage", userId);
}

@Override
public int updateAWSUserImage(UserImage userImage) {
return sqlSession.update(NS+"updateAWSUserImage", userImage);
}

@Override
public int deleteAWSUserImage(String userId) {
return sqlSession.delete(NS+"deleteAWSUserImage", userId);
}


}
