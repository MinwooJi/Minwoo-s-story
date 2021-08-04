package com.dima.test.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dima.test.dto.Image;
import com.dima.test.dto.PostAndImage;
import com.dima.test.dto.User;
import com.dima.test.dto.UserImage;

@Repository
public class UserDaoImpl implements UserDao {
	private static final String NS = "com.dima.test.dao.UserDao.";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertUser(User user) {
		return sqlSession.insert(NS+"insertUser",user);
	}

	@Override
	public String selectEmail(String id) {
		return sqlSession.selectOne(NS+"selectEmail",id);
	}

	@Override
	public int useridcount(String userId) {
		int number= sqlSession.selectOne(NS+"useridcount",userId);
		System.out.println(number);
		return number;
	}

	@Override
	public String userpwdById(String userId) {
		return sqlSession.selectOne(NS+"userpwdById",userId);
	}

	@Override
	public User loginUser(User tempuser) {
		HashMap<String, String> result = sqlSession.selectOne(NS + "loginUser",tempuser);
		User user = new User();
		user.setUserEmail(result.get("USER_EMAIL"));
		user.setUserGiturl(result.get("USER_GITURL"));
		user.setUserId(result.get("USER_ID"));
		user.setUserName(result.get("USER_NAME"));
		user.setUserNickname(result.get("USER_NICKNAME"));
		user.setUserPw(result.get("USER_PW"));
		return user;
	}

	@Override
	public void changePasswordByEmail(User user) {
		sqlSession.update(NS+"changePasswordByEmail", user);
	}

	@Override
	public int TotalCount() {
		return sqlSession.selectOne(NS+"Totalcount");
	}

	@Override
	public void updateUser(User user) {
		sqlSession.update(NS+"updateUser", user);
	}

	@Override
	public List<PostAndImage> selectPostAndImage(User user) {
		List<PostAndImage> result = sqlSession.selectList(NS + "selectPostAndImage",user);
		return result;
	}

	@Override
	public List<Image> selectFirstImage(User user) {
		List<Image>result = sqlSession.selectList(NS+"selectFirstImage",user);
		return result;
	}

	@Override
	public void deleteUser(User user) {
		sqlSession.delete(NS+"deleteUser", user);
	}

	@Override
	public int selectNickName(String nickname) {
		return sqlSession.selectOne(NS+"selectNickName",nickname);
	}

	@Override
	public User selectIdByNickname(String nickname) {
		HashMap<String, String> result = sqlSession.selectOne(NS + "selectIdByNickname",nickname);
		User user = new User();
		user.setUserEmail(result.get("USER_EMAIL"));
		user.setUserGiturl(result.get("USER_GITURL"));
		user.setUserId(result.get("USER_ID"));
		user.setUserName(result.get("USER_NAME"));
		user.setUserNickname(result.get("USER_NICKNAME"));
		user.setUserPw(result.get("USER_PW"));
		return user;
	}

	@Override
	public String selectRankingtodayMe(String userId) {
		System.out.println(userId);
		String result= sqlSession.selectOne(NS+"selectRankingtodayMe",userId);
		System.out.println(result);
		return result;
	}

	@Override
	public String selectRankingTotalMe(String userId) {
		System.out.println(userId);
		String result= sqlSession.selectOne(NS+"selectRankingTotalMe",userId);
		System.out.println(result);
		return result;
	}

	@Override
	public User userDetailById(String userId) {
		HashMap<String, String> result = sqlSession.selectOne(NS + "userDetailById",userId);
		User user = new User();
		user.setUserEmail(result.get("USER_EMAIL"));
		user.setUserGiturl(result.get("USER_GITURL"));
		user.setUserId(result.get("USER_ID"));
		user.setUserName(result.get("USER_NAME"));
		user.setUserNickname(result.get("USER_NICKNAME"));
		user.setUserPw(result.get("USER_PW"));
		
		return user;
	}

	@Override
	public User userDetailByNickname(String userNickname) {
		HashMap<String, String> result = sqlSession.selectOne(NS + "userDetailByNickname",userNickname);
		User user = new User();
		user.setUserEmail(result.get("USER_EMAIL"));
		user.setUserGiturl(result.get("USER_GITURL"));
		user.setUserId(result.get("USER_ID"));
		user.setUserName(result.get("USER_NAME"));
		user.setUserNickname(result.get("USER_NICKNAME"));
		user.setUserPw(result.get("USER_PW"));
		
		return user;
	}
	
}
