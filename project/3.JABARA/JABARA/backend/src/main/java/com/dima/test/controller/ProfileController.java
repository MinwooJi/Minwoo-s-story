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

import com.dima.test.dto.MyApply;
import com.dima.test.dto.Resume;
import com.dima.test.dto.ResumeDetail;
import com.dima.test.service.HireService;
import com.dima.test.service.ProfileService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/profile")
public class ProfileController {
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private HireService hireService;
	
	private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);
	 
	//지원한 회사 사용자 직접 입력
	@ApiOperation(value = "지원한 회사 직접 입력,  그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/insertMyApply")
	public ResponseEntity<String> insertMyApply(@RequestBody MyApply myApply) throws Exception {
		logger.debug("지원한 회사 직접 입력");
		String userId = myApply.getUserId();
		int applyCount = profileService.selectMyApplyNumber(userId);
		System.out.println("applyCount : "+applyCount);
		int applyNumber = 0;
		if(applyCount==0) {
			applyNumber=1;
		}else {
			// max 값 + 1
			applyNumber = profileService.selectMyApplyNumberMax(userId) + 1;  
		}
		myApply.setMyApplyNumber(applyNumber);
		
		if(myApply.getCompanyImage().equals("true")) {
		String userid=myApply.getUserId();
		String companyimg="https://jobara.s3.ap-northeast-2.amazonaws.com/myhireinfo//"+userid+applyNumber;
		System.out.println(companyimg);
		myApply.setCompanyImage(companyimg);
		}else {
			myApply.setCompanyImage("");
		}
		
		if (profileService.insertMyApply(myApply)) {
			return new ResponseEntity<String>(applyNumber+"", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT );
	}
	//지원한 회사 보여주기
	@ApiOperation(value = "지원한 회사 리스트", response = List.class)
	@GetMapping("/selectMyApply/{userId}")
	public ResponseEntity<List<MyApply>> selectMyApply(@PathVariable String userId) throws Exception {
		logger.debug("selectMyApply - 호출");
		return new ResponseEntity<List<MyApply>>(profileService.selectMyApply(userId), HttpStatus.OK);
	}
	
	//지원한 회사 리스트에서 삭제하기
	@ApiOperation(value = "지원한 회사 리스트 삭제", response = List.class)
	@GetMapping("/deleteMyApply/{myApplyNumber}")
	public ResponseEntity<String> deleteMyApply(@PathVariable int myApplyNumber) throws Exception {
		logger.debug("deleteMyApply - 호출");
		if (profileService.deleteMyApply(myApplyNumber)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT );
	}
	
	//자기소개서 리스트 보여주기
	@ApiOperation(value = "자기소개서 리스트", response = List.class)
	@GetMapping("/selectMyResume/{userId}")
	public ResponseEntity<List<Resume>> selectMyResume(@PathVariable String userId) throws Exception {
		logger.debug("selectMyResume - 호출");
		return new ResponseEntity<List<Resume>>(profileService.selectMyResume(userId), HttpStatus.OK);
	}
	
	//자기소개서 상세 띄어주기
	@ApiOperation(value = "자기소개서 상세 페이지", response = List.class)
	@PostMapping("/selectResumeDetail")
	public ResponseEntity<List<ResumeDetail>> selectResumeDetail(@RequestBody Resume resume) throws Exception {
		logger.debug("selectResumeDetail - 호출");
		return new ResponseEntity<List<ResumeDetail>>(profileService.selectResumeDetail(resume), HttpStatus.OK);
	}
	
	//자기소개서 입력하기
	@ApiOperation(value = "자기소개서 입력,  그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/insertResume")
	public ResponseEntity<String> insertResume(@RequestBody Resume resume) throws Exception {
		logger.debug("자기소개서 입력");
		String userId = resume.getUserId();
		int resumeCount = profileService.selectResumeNumber(userId);
		System.out.println(resumeCount);
		int resumeNumber = 0;
		if(resumeCount==0) {
			resumeNumber = 1;
		}else {
			// max넘버로 지정
			resumeNumber = profileService.selectResumeNumberMax(userId)+1;
		}
		resume.setResumeNumber(resumeNumber);
		if (profileService.insertResume(resume)) {
			return new ResponseEntity<String>(resumeNumber+"", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT );
	}
	
	   //자기소개서 문항 추가하기
	   @ApiOperation(value = "자기소개서 문항 추가,  그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	   @PostMapping("/insertQuestion")
	   public ResponseEntity<String> insertQuestion(@RequestBody ResumeDetail resumeDetail) throws Exception {
	      logger.debug("자기소개서 문항 입력");
//	      int qNumberCount = profileService.selectQuestionNumber(resumeDetail);
//	      int qNumber = 0;
//	      if(qNumberCount==0) {
//	    	  qNumber = 1;
//	      }else {
//	    	  //Max값 구하기
//	    	  qNumber = profileService.selectQuestionNumberMax(resumeDetail)+1;
//	      }
//	      resumeDetail.setQuestionNumber(qNumber);
	      if (profileService.insertQuestion(resumeDetail)) {
	         return new ResponseEntity<String>("success", HttpStatus.OK);
	      }
	      return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT );
	   }
	
	//자기소개서 수정하기
	@ApiOperation(value = "자기소개서 수정,  그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/updateResume")
	public ResponseEntity<String> updateResume(@RequestBody Resume resume) throws Exception {
		logger.debug("자기소개서 수정");
		if (profileService.updateResume(resume)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT );
	}
	
	//자기소개서 문항 수정하기
		@ApiOperation(value = "자기소개서 문항 수정,  그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
		@PostMapping("/updateResumeDetail")
		public ResponseEntity<String> updateResumeDetail(@RequestBody ResumeDetail resumedetail) throws Exception {
			logger.debug("자기소개서 문항 수정");
			if (profileService.updateResumeDetail(resumedetail)) {
				return new ResponseEntity<String>("success", HttpStatus.OK);
			}
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT );
		}
	
	//자기소개서 삭제하기
	@ApiOperation(value = "자기소개서 삭제,  성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/deleteResume")
	public ResponseEntity<String> deleteResume(@RequestBody Resume resume) throws Exception {
		logger.debug("자기소개서 삭제");
		if (profileService.deleteResume(resume)) {
		return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT );
	}
		
	//자기소개서 문항을 삭제하기
	@ApiOperation(value = "자기소개서 문항 삭제,  성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/deleteQuestion")
	public ResponseEntity<String> deleteQuestion(@RequestBody ResumeDetail resumeDetail) throws Exception {
		logger.debug("자기소개서 문항 삭제");
		if (profileService.deleteQuestion(resumeDetail)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT );
	}
}
