package com.dima.test.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.InternetAddress;

import com.dima.test.dto.Comment;
import com.dima.test.dto.Image;
import com.dima.test.dto.PostAndImage;
import com.dima.test.dto.RankUser;
import com.dima.test.dto.User;
import com.dima.test.dto.UserImage;
import com.dima.test.service.ImageService;
import com.dima.test.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userservice;
	@Autowired
	private ImageService imageservice;
	@Autowired
	private JavaMailSender mailSender;
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	// ????????????
	@ApiOperation(value = "??????????????? ????????????. ????????? DB?????? ??????????????? ?????? 'success' ?????? 'fail' ???????????? ????????????.", response = String.class)
	@PostMapping
	public ResponseEntity<String> registUser(@RequestBody User user) {
		logger.debug("registuser - ??????");
		UserImage file=new UserImage();
		String destinationFileName="jobaraprofile//user.png";
		file.setUserId(user.getUserId());
		file.setUserimagePath(destinationFileName);
		
		if (userservice.insertUser(user)) {
			imageservice.insertAWSUserImage(file);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}

	// USER DETAIL by ID
	@ApiOperation(value = "?????? ????????? ????????????.", response = String.class)
	@GetMapping("/userDetailById/{userId}")
	public ResponseEntity<User> userDetailById(@PathVariable String userId) throws Exception {
		logger.debug("userDetailById - ??????");
		return new ResponseEntity<User>(userservice.userDetailById(userId), HttpStatus.OK);
	}
	
	// USER DETAIL by NICKNAME
 	@ApiOperation(value = "?????? ????????? ????????????.", response = String.class)
	@GetMapping("/userDetailByNickname/{userNickname}")
	public ResponseEntity<User> userDetailByNickname(@PathVariable String userNickname) throws Exception {
		logger.debug("userDetailById - ??????");
		return new ResponseEntity<User>(userservice.userDetailByNickname(userNickname), HttpStatus.OK);
	}
	
	// ?????????????????? email??? ?????????
	@ApiOperation(value = "??????????????????", response = String.class)
	@PostMapping("/passwordfind")
	public ResponseEntity<String> findPass(@RequestBody User user) {
		logger.debug("registuser - ??????");
		Random r = new Random();
		int dice = r.nextInt(157211) + 48271;
		String dicesen = Integer.toString(dice);
		String tomail = userservice.selectEmailById(user.getUserId());
		if (!tomail.equals(user.getUserEmail())) {
			return new ResponseEntity<String>("fail", HttpStatus.OK);
		} else {
			String title = "???????????? ?????? ?????? ????????? ?????????.";
			String content = System.getProperty("line.separator") + System.getProperty("line.separator")
					+ "??????????????? ????????? ?????? ??????????????? ??????????????? ???????????????" + System.getProperty("line.separator")
					+ System.getProperty("line.separator") + "???????????? ?????? ??????????????? " + dice + " ?????????. "
					+ System.getProperty("line.separator") + System.getProperty("line.separator")
					+ "????????? ??????????????? ??????????????? ????????? ????????? ???????????? ???????????????.";
			try {
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
				messageHelper.setTo(tomail); // ???????????? ?????????
				messageHelper.setSubject(title); // ??????????????? ????????? ????????????
				messageHelper.setText(content); // ?????? ??????
				message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(tomail));
				mailSender.send(message);

			} catch (Exception e) {
				System.out.println("MessagingException");
				e.printStackTrace();
			}
			return new ResponseEntity<String>(dicesen, HttpStatus.OK);
		}
	}

	// ???????????? ??????
	@ApiOperation(value = "??????????????????", response = String.class)
	@PostMapping("/passwordchangeByEmail")
	public ResponseEntity<String> passwordchangeByEmail(@RequestBody User user) {
		logger.debug("changePasswordByEmail - ??????");
		userservice.modifyBypassword(user);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	// ?????????
	@PostMapping("/login")
	@ApiOperation(value = "?????????", response = String.class)
	public ResponseEntity<User> Login(@RequestBody User user) {
		logger.debug("login - ??????");
		System.out.println(user.getUserId());
		String userId = user.getUserId();
		String pwd = user.getUserPw();
		System.out.println(user.getUserPw());
		int userCount = userservice.getUserCount(userId);
		System.out.println("?????????1");
		User loginuser = new User();
		if (userCount == 1) {
			String pw = userservice.getPwdById(userId);
			System.out.println("?????????2");
			if (pwd.equals(pw)) {
				User tempuser = new User();
				tempuser.setUserId(userId);
				tempuser.setUserPw(pwd);
				loginuser = userservice.login(tempuser);
				System.out.println("?????????3");
			}
		}
		return new ResponseEntity<User>(loginuser, HttpStatus.OK);
	}

	// ???????????? ?????? ??????
	@PutMapping("/modifyuser")
	public ResponseEntity<String> modify(@RequestBody User user) {
		logger.debug("modifyuser - ??????");
		userservice.modifyUser(user);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	// ????????? ????????? ??????
	@PostMapping("/modifyuser/nickname")
	public ResponseEntity<String> modify(@RequestBody String nickname) {
		logger.debug("modifynickname - ??????");
		int count = userservice.selectNickName(nickname);
		String result = "";
		// ???????????? ????????? ?????????
		if (count == 0) {
			result = "success";
		}
		// ???????????? ????????? ?????????
		else if (count == 1) {
			result = "fail";
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	// detail feed
	@PostMapping("/detail")
	public ResponseEntity<List<PostAndImage>> detail(@RequestBody User user) {
		logger.debug("detail - ??????");
		List<PostAndImage> postAndimage = userservice.detail(user);
		return new ResponseEntity<List<PostAndImage>>(postAndimage, HttpStatus.OK);
	}

	@PostMapping("/seconddetail")
	public ResponseEntity<List<Image>> seconddetail(@RequestBody User user) {
		logger.debug("seconddetail - ??????");
		List<Image> postFirstImage = userservice.seconddetail(user);
		return new ResponseEntity<List<Image>>(postFirstImage, HttpStatus.OK);
	}

	// ?????? ??????
	@DeleteMapping("/deleteUser")
	public ResponseEntity<String> deleteUser(@RequestBody User user) {
		logger.debug("deleteUser - ??????");
		userservice.deleteUser(user);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@PostMapping("/detail/differentUser")
	public ResponseEntity<List<PostAndImage>> detaildifferentUser(@RequestBody String nickname) {
		logger.debug("detaildifferent - ??????");
		// ??????????????? ID????????????
		User user = userservice.selectIdByNickname(nickname);
		List<PostAndImage> postAndimage = userservice.detail(user);
		return new ResponseEntity<List<PostAndImage>>(postAndimage, HttpStatus.OK);
	}

	@PostMapping("/seconddetail/differentUser")
	public ResponseEntity<List<Image>> seconddetaildifferentUser(@RequestBody String nickname) {
		logger.debug("detaildifferent - ??????");
		// ??????????????? ID????????????
		User user = userservice.selectIdByNickname(nickname);
		List<Image> postFirstImage = userservice.seconddetail(user);
		return new ResponseEntity<List<Image>>(postFirstImage, HttpStatus.OK);
	}

	@ApiOperation(value = "????????? ??????????????? ????????????????????? Ranking ????????? ????????????.", response = String.class)
	@PostMapping("/rankingtodayMe")
	public ResponseEntity<String> rankingtodayMe(@RequestBody String userId) throws Exception {
		logger.debug("ranking ?????? ??????");
		String rank = userservice.selectRankingtodayMe(userId);
		return new ResponseEntity<String>(rank, HttpStatus.OK);
	}

	@ApiOperation(value = "????????? ??????????????? ????????????????????? Ranking ????????? ????????????.", response = String.class)
	@PostMapping("/rankingTotalMe")
	public ResponseEntity<String> rankingWeekMe(@RequestBody String userId) throws Exception {
		logger.debug("ranking ?????? ??????");
		String rank = userservice.selectRankingTotalMe(userId);
		return new ResponseEntity<String>(rank, HttpStatus.OK);
	}

//	@RequestMapping(value="/korean", method=RequestMethod.POST)
//	public String korean(@RequestParam String name, Model model) throws Exception {
//		System.out.println("name=" + name);
//		model.addAttribute("name", name);
//		return "hello2";
//	}
//	
//	@RequestMapping("/hello2")
//	public void hello(Model model) throws Exception {
//		String uid = "sinhye";
//		
//		
//		User users = userservice.userDetailById(uid);
//		model.addAttribute("user", users);
//		model.addAttribute("users", users);
//	}

}
