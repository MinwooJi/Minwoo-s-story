package com.dima.test.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dima.test.dto.PostLikeTable;

@Repository
public class alrimDaoImpl implements alrimDao {

	private static final String NS = "com.dima.test.dao.alrimDao.";
	@Autowired
	SqlSession sqlssesion;

	@Override
	public void insertLike(PostLikeTable postlike) {
		sqlssesion.insert(NS + "insertLike", postlike);
	}

	@Override
	public void deleteLike(PostLikeTable postlike) {
		sqlssesion.delete(NS + "deleteLike", postlike);

	}

	@Override
	public String selectTargetUser(int postNumber) {
		return sqlssesion.selectOne(NS + "selectTargetUserId", postNumber);
	}

	@Override
	public List<PostLikeTable> selectList(String userId) {
		List<HashMap<String, Object>> result = sqlssesion.selectList(NS + "selectList", userId);
		System.out.println("12312312312312312311111111111111111111111");
		System.out.println(result.size());
		List<PostLikeTable> list = new ArrayList<PostLikeTable>();

		for (int i = 0; i < result.size(); i++) {
			PostLikeTable temp = new PostLikeTable();
			for (Entry<String, Object> elem : result.get(i).entrySet()) {
				if (elem.getKey().equals("TARGETUSER_ID")) {
					System.out.println("targetuserid: "+elem.getValue());
					temp.setTargetuserId((String)elem.getValue());
				}
				if (elem.getKey().equals("LIKE_DATE")) {
					System.out.println("likedate: "+elem.getValue());
					temp.setLikeDate((Timestamp)elem.getValue());
				}
				if (elem.getKey().equals("POST_NUMBER")) {
					System.out.println("postnumber: "+elem.getValue());
					temp.setPostNumber((int)elem.getValue());
				}
				if(elem.getKey().equals("USER_ID")){
					System.out.println("userid: "+elem.getValue());
					temp.setUserId((String)elem.getValue());
				}
			}
			list.add(temp);
		}
		return list;
	}

	@Override
	public int selectLikePost(PostLikeTable postlike) {
		return sqlssesion.selectOne(NS + "selectLikePost", postlike);
	}

}
