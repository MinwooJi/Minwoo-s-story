package com.dima.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dima.test.dao.alrimDao;
import com.dima.test.dto.PostLikeTable;
import com.dima.test.dto.TagAndUserId;
import com.dima.test.websocket.websockethandler.AlrimApplicantWebSocketHandler;

@Service
public class alrimServiceImpl implements alrimService {

	private AlrimApplicantWebSocketHandler handler;
	@Autowired
	alrimDao dao;
	@Override
	public void insertLike(PostLikeTable postlike) {
		dao.insertLike(postlike);
	}
	@Override
	public void deleteLike(PostLikeTable postlike) {
		dao.deleteLike(postlike);
	}
	@Override
	public String selectTargetUser(int postNumber) {
		return dao.selectTargetUser(postNumber);
	}
	@Override
	public List<PostLikeTable> selectList(String userId) throws Exception{
		List<PostLikeTable> alrim = dao.selectList(userId);
		System.out.println("끝내고~");
		for(PostLikeTable table : alrim) {
			System.out.println(table.getPostNumber());
			System.out.println(table.getTargetuserId());
			System.out.println(table.getLikeDate());
			System.out.println("띠용");
			String targetId = table.getTargetuserId();
			System.out.println(targetId);
			try {
				System.out.println("실행을 하려곤 하니??/");
				handler.noticeCourseApplicant(String.valueOf(targetId));
	        } catch (NullPointerException npe) {
	            System.out.println(" Null Opint Exception!!");
	        }
		}
		System.out.println("여기??");
		return alrim;
	}
	@Override
	public int selectLikePost(PostLikeTable postlike) {
		return dao.selectLikePost(postlike);
	}

}
