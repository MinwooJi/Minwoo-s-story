package com.ssafy.hrm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.hrm.dto.DepCountDto;
import com.ssafy.hrm.dto.DepartmentDto;
import com.ssafy.hrm.dto.DepartmentEmpDto;
import com.ssafy.hrm.dto.DeptEmpsDto;
import com.ssafy.hrm.dto.EmployeeDto;
import com.ssafy.hrm.dto.EmployeeDto2;
import com.ssafy.hrm.help.BoolResult;
import com.ssafy.hrm.help.NumberResult;
import com.ssafy.hrm.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//http://localhost:8080/ssafy/swagger-ui.html
@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value="SSAFY")
public class EmployeeController {
	
	public static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeService  employeeService; 


    @ApiOperation(value = "모든 사원의 정보를 반환한다.", response = List.class)
	@RequestMapping(value = "/employee/all", method = RequestMethod.GET)
	public ResponseEntity<List<EmployeeDto>> findAllEmployees() throws Exception {
		logger.info("1-------------findAllEmployees-----------------------------"+new Date());
		List<EmployeeDto> emps = employeeService.findAllEmployees();
		if (emps.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<EmployeeDto>>(emps, HttpStatus.OK);
	}
    
    @ApiOperation(value = " 새로운 사원을 정보를 입력한다. 그리고 그 사원의 사원번호를 반환한다.", response = NumberResult.class)
   	@RequestMapping(value = "/employee", method = RequestMethod.POST)
   	public ResponseEntity<NumberResult> addEmployee(@RequestBody EmployeeDto dto) throws Exception {
   		logger.info("5-------------addEmployee-----------------------------"+new Date());
   		logger.info("5-------------addEmployee-----------------------------"+dto);
   		int total = employeeService.addEmployee(dto);
   		NumberResult nr=new NumberResult();
   		nr.setCount(total);
   		nr.setName("addEmployee");
   		nr.setState("succ");
   		logger.info("5-------------addEmployee-------id------------------"+total);
   		if (total<=0) {
   			nr.setCount(-1);
   	   		nr.setName("addEmployee");
   	   		nr.setState("fail");
   			//return new ResponseEntity(HttpStatus.NO_CONTENT);
   			return new ResponseEntity<NumberResult>(nr, HttpStatus.OK);
   		}
   		return new ResponseEntity<NumberResult>(nr, HttpStatus.OK);
   	}
}
