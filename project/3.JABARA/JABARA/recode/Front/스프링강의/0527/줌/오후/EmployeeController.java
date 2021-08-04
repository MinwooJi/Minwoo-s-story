package com.ssafy.hrm.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.hrm.dto.Employee;
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

    @SuppressWarnings("unchecked")
	@ApiOperation(value = "모든 사원의 정보를 반환한다.", response = List.class)
	@RequestMapping(value = "/employee/all", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> findAllEmployees() throws Exception {
		logger.info("1-------------findAllEmployees-----------------------------"+new Date());
		List<Employee> emps = employeeService.findAllEmployees();
		if (emps.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Employee>>(emps, HttpStatus.OK);
	}

    @ApiOperation(value = "사원의 상세 정보를 반환한다.", response = Employee.class)
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employee> findEmployee(@PathVariable int id) throws Exception {
    	return new ResponseEntity<Employee>(
    			employeeService.findEmployeeById(id), HttpStatus.OK
    	);
    }
    
    @ApiOperation(value = " 새로운 사원을 정보를 입력한다. 그리고 그 사원의 사원번호를 반환한다.", response = String.class)
   	@RequestMapping(value = "/employee", method = RequestMethod.POST)
   	public ResponseEntity<String> addEmployee(Employee dto) throws Exception {
   		logger.info("5-------------addEmployee-----------------------------"+dto);
   		if(employeeService.addEmployee(dto)) return new ResponseEntity<String>("success", HttpStatus.OK);
   		else return new ResponseEntity<String>("fail", HttpStatus.OK); 
   	}
    
    @ApiOperation(value = " 사원의 정보를 수정한다 만약 사원의 부서와 업무가 변경되면 잡히스토리에  자동(trigger)으로 추가된다. 원래 put이다.", response = String.class)
   	@RequestMapping(value = "/employee", method = RequestMethod.PUT)
   	public ResponseEntity<String> updateEmployee(@RequestBody Employee dto) throws Exception {
   		logger.info("1-------------updateEmployee-----------------------------"+dto);

   		if (employeeService.updateEmployee(dto)) {
   			return new ResponseEntity<String>("success", HttpStatus.OK);
   		}
   		return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
   	}
    
    @ApiOperation(value = " 해당사원의 정보를 삭제한다. 사원의 정보를 삭제하기전, 정산하고 , 잡히스토리 수정등 여러 작업을 해야한다. 여기서는 히스토리를 모두 지우고(수정한 적이 없다면 바로 삭제가능) 삭제할 수 있다 . 원래 delete다", response = String.class)
	@DeleteMapping(value = "/employee/{employeeId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable int employeeId) throws Exception {
		logger.info("1-------------deleteEmployee-----------------------------"+employeeId);
		if (employeeService.deleteEmployee(employeeId)) {
   			return new ResponseEntity<String>("success", HttpStatus.OK);
   		}
   		return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
	}
}
