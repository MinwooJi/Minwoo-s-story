package com.dima.test.service;

import java.util.List;

import com.dima.test.dto.AlgoCategoryCount;
import com.dima.test.dto.Post;

public interface ProfileAlgorithmService {
	AlgoCategoryCount selectList(String userId);

}
