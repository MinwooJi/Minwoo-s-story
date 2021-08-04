package com.ssafy.hrm.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.hrm.dto.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	private final static String NS = "com.ssafy.hrm.EmployeeDAO.";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Employee> selectEmployee() {
		return sqlSession.selectList(NS + "findAllEmployees");
	}
	

	@Override
	public int insertEmployee(Employee emp) throws Exception {
		return sqlSession.insert(NS + "addEmployee",emp);
	}


	@Override
	public Employee selectEmployeeById(int id) throws Exception {
		return sqlSession.selectOne(NS + "findEmployeeById",id);
	}

	@Override
	public boolean updateEmployee(Employee emp) throws Exception {
		 sqlSession.update(NS + "updateEmployee",emp);
		 return true;
	}

	@Override
	public int deleteEmployee(int id) {
		return sqlSession.delete(NS + "deleteEmployee",id);
	}


	@Override
	public int selectEmployeeMaxId() {
		return sqlSession.selectOne(NS + "findAfterAdd");
	}

	
}
