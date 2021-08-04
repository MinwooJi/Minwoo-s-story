package com.ssafy.myapp.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class EmpDAOImpl implements EmpDAO{

    SqlSession sqlSession;
	
	@Override
	public List<String> selectAll() {
		return sqlSession.selectList("emp.selectName");
	}

}


