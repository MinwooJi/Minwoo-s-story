package com.dima.test.controller;

import java.util.HashMap;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dima.test.dto.User;
import com.dima.test.service.KakaoAPI;
import com.dima.test.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/kakao")
public class KakaoController {
	@Autowired
	private UserService userservice;
	@Autowired
	private KakaoAPI kakao;
	
	@ApiOperation(value = "카카오로그인", response = String.class)
	@PostMapping(value="/logout")
	public ResponseEntity<String> logout(@RequestBody String accessToken) {
		int indexsame = 0;
		indexsame= accessToken.indexOf("=");
		String token = accessToken.substring(indexsame+1, accessToken.length());
		kakao.kakaoLogout(token);
		 return new ResponseEntity<String>("logout", HttpStatus.OK);
	}

	@ApiOperation(value = "카카오로그인", response = User.class)
	@PostMapping("/login")
	 public ResponseEntity<User> login(@RequestBody String accessToken) {
	    //String access_Token = kakao.getAccessToken(code);
	    
	    int indexsame = 0;
	    indexsame= accessToken.indexOf("=");
	    String token = accessToken.substring(indexsame+1, accessToken.length());
	    HashMap<String,Object>userInfo =kakao.getUserInfo(token);
	    User loginuser=new User();
	    String id;
	    String nickname;
	    Object temp;
	    if(userInfo!=null) {
	        temp= userInfo.get("id");
	        temp=temp+"";
	        id= temp+"kakao.com";
	        if(userservice.getUserCount(id)>0) {
	            User user= new User();
	            user.setUserId(id);
	            loginuser=userservice.login(user);
	        }
	        else if(userservice.getUserCount(id)==0) {
	            Random r = new Random();
	            int dice = r.nextInt(157211)+48271;
	            String dicesen=Integer.toString(dice);
	            String pw = dicesen;
	            loginuser.setUserId(id);
	            loginuser.setUserPw(pw);
	            int totalcount=userservice.getTotalCount();
	            loginuser.setUserName(totalcount+"");
	            nickname= temp+"";
	            loginuser.setUserNickname(nickname);
	            userservice.insertUser(loginuser);
	        }
	    }
	    return new ResponseEntity<User>(loginuser, HttpStatus.OK);
	}
}
