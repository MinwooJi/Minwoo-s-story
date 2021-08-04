package com.dima.test.dao;

import java.util.List;

import com.dima.test.dto.AlgoCategoryCount;
import com.dima.test.dto.Post;

public interface ProfileAlgorithmDao {

	AlgoCategoryCount selectList(String userId);

	void insertUser(String userId);


}
