package com.ssafy.hrm.service;

import java.util.List;

import com.ssafy.hrm.dto.Employee;


public interface EmployeeService {
	public List<Employee> findAllEmployees();
	public Employee findEmployeeById(int id);
	public boolean addEmployee(Employee emp);
	public boolean updateEmployee(Employee emp);
	public boolean deleteEmployee(int id);
}
