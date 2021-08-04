package com.dima.test.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dima.test.dto.AlgoCategoryCount;
import com.dima.test.dto.Post;

@Repository
public class ProfileAlgorithmDaoImpl implements ProfileAlgorithmDao {
	private static final String NS =  "com.dima.test.dao.ProfileAlgorithmDao.";
	@Autowired
	SqlSession sqlsseion;
	@Override
	public AlgoCategoryCount selectList(String userId) {
		HashMap<String, Integer> result = sqlsseion.selectOne(NS+"selectList", userId);
		AlgoCategoryCount temp = new AlgoCategoryCount();
		temp.setDfsBfs(result.get("DFS_BFS"));
		temp.setBinary(result.get("BINARY"));
		temp.setBrouteForce(result.get("BROUTE_FORCE"));
		temp.setDynamic(result.get("DYNAMIC"));
		temp.setEtc(result.get("ETC"));
		temp.setGraph(result.get("GRAPH"));
		temp.setGreedy(result.get("GREEDY"));
		temp.setHash(result.get("HASH"));
		temp.setHeap(result.get("HEAP"));
		temp.setSimulation(result.get("SIMULATION"));
		temp.setSort(result.get("SORT"));
		temp.setStackQueue(result.get("STACK_QUEUE"));
		temp.setUserId(userId);
		return temp;
	}
	@Override
	public void insertUser(String userId) {
		sqlsseion.insert(NS+"insertUser",userId);
	}

}
