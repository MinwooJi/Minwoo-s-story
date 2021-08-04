package com.ssafy.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.hrm.dao.EmployeeDAO;
import com.ssafy.hrm.dto.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
    @Autowired
	private EmployeeDAO employeeDao;

    @Override
	public List<Employee> findAllEmployees() {
		return employeeDao.selectEmployee();
	}
    
  	@Override
	public boolean addEmployee(Employee emp) {
  		emp.setId(employeeDao.selectEmployeeMaxId());
		return employeeDao.insertEmployee(emp) == 1;
	}


	@Override
	public Employee findEmployeeById(int id) {
		return employeeDao.selectEmployeeById(id);
	}

	@Override
	@Transactional
	public boolean updateEmployee(Employee emp) {
		return employeeDao.updateEmployee(emp) == 1;
	}

	@Override
	@Transactional
	public boolean deleteEmployee(int id) {
		return employeeDao.deleteEmployee(id) == 1;
	}
	
}
