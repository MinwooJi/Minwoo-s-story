package com.dima.test.service;
import java.util.Dictionary;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dima.test.dao.DictionaryDao;
import com.dima.test.dao.FollowDao;
import com.dima.test.dao.UserDao;
import com.dima.test.dto.Follow;
import com.dima.test.dto.Image;
import com.dima.test.dto.PostAndImage;
import com.dima.test.dto.User;
import com.dima.test.dto.UserImage;

@Service
public class DictionaryServiceImpl implements DictionaryService{

	@Autowired
	DictionaryDao dao;
	
	@Override
	public List<Dictionary> selectDictionaryAll() {
		return dao.selectDictionaryAll();
	}

	@Override
	public List<Dictionary> selectDictionaryByName(String word) {
		return dao.selectDictionaryByName(word);
	}
	

}
