package com.dima.test.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dima.test.dto.Hire;
import com.dima.test.dto.MyApply;
import com.dima.test.service.HireService;
import com.dima.test.service.MycalService;
import com.dima.test.service.ProfileService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/Hire")
public class HireController {
	@Autowired
	private HireService hireService;
	
	@Autowired
	private MycalService mycalService;
	
	@Autowired
	private ProfileService profileService;
	
	private static final Logger logger = LoggerFactory.getLogger(HireController.class);
	
	//취업 일정 넘겨주기
	@ApiOperation(value = "취업 일정 넘겨주기", response = List.class)
	@GetMapping("/selectHire")
	public ResponseEntity<List<Hire>> selectHire() throws Exception {
		logger.debug("selectHire - 호출");
		return new ResponseEntity<List<Hire>>(hireService.selectHire(), HttpStatus.OK);
	}
	
//	//취업 정보 회사 이름으로 검색
//	@ApiOperation(value = "취업 일정  검색", response = List.class)
//	@GetMapping("/selectHire")
//	public ResponseEntity<List<Hire>> selectHire() throws Exception {
//		logger.debug("selectHire - 호출");
//		return new ResponseEntity<List<Hire>>(hireService.selectHire(), HttpStatus.OK);
//	}
	
	//취업 일정 입력
	@ApiOperation(value = "취업 일정 입력", response = List.class)
	@PostMapping("/insertHire")
	public ResponseEntity<String> insertHire(@RequestBody Hire hire) throws Exception {
		logger.debug("insertHire - 호출");
		if (hireService.insertHire(hire)) {
			int hirenum = hireService.selectMaxHireNumber();			
			if(hire.getCompanyImage().equals("true")) {
				String companyimg="https://jobara.s3.ap-northeast-2.amazonaws.com/hireinfo//"+hirenum;
				hire.setCompanyImage(companyimg);
			}
			else {
			hire.setCompanyImage("");
			}
			if (hireService.updateHire(hire)) {
			return new ResponseEntity<String>(hirenum+"", HttpStatus.OK);
			}
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT );
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT );
	}

	//취업 일정 수정
	@ApiOperation(value = "취업 일정 수정", response = List.class)
	@PostMapping("/updateHire")
	public ResponseEntity<String> updateHire(@RequestBody Hire hire) throws Exception {
		logger.debug("updateHire - 호출");
		if (hireService.updateHire(hire)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT );
	}
	
	//취업 일정 삭제
	@ApiOperation(value = "취업 일정 삭제", response = List.class)
	@PostMapping("/deleteHire/{hireNumber}")
	public ResponseEntity<String> deleteHire(@PathVariable int hireNumber) throws Exception {
		logger.debug("deleteHire - 호출");
		if (hireService.deleteHire(hireNumber)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT );
	}
	
	//마이 캘린더에 취업 일정 추가
	@ApiOperation(value = "마이 캘린더에 취업 일정 추가", response = List.class)
	@PostMapping("/insertHireToMyCal")
	public ResponseEntity<String> insertHireToMyCal(@RequestBody Hire hire) throws Exception {
		logger.debug("insertHireToMyCal - 호출");
		// 중복된 데이터가 이미 들어갔는지 확인하는 부분
		System.out.println(hire.getHireNumber());
		if(mycalService.selectHireNumber(hire)!=0) {
			System.out.println("이미 추가한 일정입니다.");
			return new ResponseEntity<String>("이미 추가한 일정입니다.",HttpStatus.NO_CONTENT);
		}
		else {
			int calNumber;
			if(hireService.selectMyCalNumberCount(hire.getUserId())==0) {
				calNumber = 1;
			}else {
				calNumber = hireService.selectMyCalNumberMax(hire.getUserId()) + 1;			
			}
			hire.setMyCalNumber(calNumber);
			if (hireService.insertHireToMyCal(hire)) {
				return new ResponseEntity<String>("success", HttpStatus.OK);
			}
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT );
		}
	}
	
	//나의 지원 내역에 지원한 회사 추가
	@ApiOperation(value = "지원한 회사 채용 일정에서 추가,  그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/insertMyApplyFromHire")
	public ResponseEntity<String> insertMyApplyFromHire(@RequestBody Hire hire) throws Exception {
		logger.debug("지원한 회사 채용 일정에서 추가");
		// MY_APPLY에 아무것도 없을 때
		System.out.println(hireService.selectHireNumber(hire));
		if(profileService.selectMyApplyNumber(hire.getUserId())==0) {
			int num = 1;
			Hire hire2 = hireService.selectHireForMyApply(hire.getHireNumber());
			hire2.setMyApplyNumber(num);
			hire2.setUserId(hire.getUserId());
			hireService.insertMyApplyFromHire(hire2);
			return new ResponseEntity<String>(num+"", HttpStatus.OK);
		//MY APPLY에 내용이 있고, 추가되지 않은 일정일 때
		}else if(hireService.selectHireNumber(hire)==0) {
			int num = profileService.selectMyApplyNumber(hire.getUserId())+1;
			System.out.println(num);
			Hire hire2 = hireService.selectHireForMyApply(hire.getHireNumber());
			System.out.println(hire.getHireNumber());
			hire2.setMyApplyNumber(num);
			hire2.setUserId(hire.getUserId());
			hireService.insertMyApplyFromHire(hire2);
			return new ResponseEntity<String>(num+"", HttpStatus.OK);
		}else {
			//MY APPLY에 내용이 있고, 추가된 일정일 때
			return new ResponseEntity<String>("이미 지원목록에 추가된 일정입니다.", HttpStatus.NO_CONTENT );
		}
	}
}
