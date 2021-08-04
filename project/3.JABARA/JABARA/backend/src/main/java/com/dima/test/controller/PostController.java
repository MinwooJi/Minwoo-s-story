package com.dima.test.controller;

import java.util.ArrayList;
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

import com.dima.test.dto.Post;
import com.dima.test.dto.PostImage;
import com.dima.test.dto.RankUser;
import com.dima.test.dto.User;
import com.dima.test.dto.UserImage;
import com.dima.test.service.ImagePostService;
import com.dima.test.service.PostService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/post")
public class PostController {
	@Autowired
	private PostService postService;
	
	@Autowired
	private ImagePostService imagePostService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	

	@ApiOperation(value = "모든 peed 게시판의 정보를 반환한다.", response = List.class)
	   @GetMapping("/selectPostInfLoading/{start}")
	   public ResponseEntity<List<Post>> selectPostInfLoading(@PathVariable int start) throws Exception {
	      logger.debug("selectPostInfLoading - 호출");
	      List<Post> result = postService.selectPostInfLoading(start);

	      return new ResponseEntity<List<Post>>(result, HttpStatus.OK);
	   }
	@ApiOperation(value = "모든 peed 게시판의 정보를 반환한다.", response = List.class)
	@GetMapping("/searchPost")
	public ResponseEntity<List<Post>> searchPost() throws Exception {
		logger.debug("searchPost - 호출");
		List<Post> result = new ArrayList<>();
		List<Post> post = postService.searchPost();
		// IMAGE_PATH 불러오기
				for(Post post2 : post) {
					String imagePath = imagePostService.selectPostImage(post2.getPostNumber());
					post2.setImagePath(imagePath);
					result.add(post2);
				}
		return new ResponseEntity<List<Post>>(post, HttpStatus.OK);
	}
	
	@ApiOperation(value = "자신이 팔로우 한 사람들의 게시물 목록", response = Post.class)
	@GetMapping("searchPostByFollowing/{userId}")
	public ResponseEntity<List<Post>> searchPostByFollowing(@PathVariable String userId) throws Exception {
		logger.debug("자신이 팔로우 한 사람들의 게시물 목록- 호출");
		return new ResponseEntity<List<Post>>(postService.searchPostByFollowing(userId), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "글 번호와 같은 peed 게시판의 정보를 반환한다.", response = Post.class)
	@GetMapping("searchPostByNo/{postNumber}")
	public ResponseEntity<Post> searchPostByNo(@PathVariable int postNumber) throws Exception {
		Post post = postService.searchPostByNo(postNumber);
		
		// IMAGE_PATH 불러오기
		String imagePath = imagePostService.selectPostImage(postNumber);
		post.setImagePath(imagePath);
		logger.debug("글 번호로 검색된 Sns 게시물 - 호출");
		return new ResponseEntity<Post>(post, HttpStatus.OK);
	}
	
	@ApiOperation(value = "작성자로 게시글 검색", response = List.class)
	@GetMapping("searchPostById/{userId}")
	public ResponseEntity<List<Post>> searchPostById(@PathVariable String userId) throws Exception {
		List<Post> result = new ArrayList<>();
		List<Post> post = postService.searchPostById(userId);
		// IMAGE_PATH 불러오기
		for(Post post2 : post) {
			String imagePath = imagePostService.selectPostImage(post2.getPostNumber());
			post2.setImagePath(imagePath);
			result.add(post2);
		}
		logger.debug("searchPostById - 호출");
		return new ResponseEntity<List<Post>>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "푼 문제를 알고리즘 페이지에서 보여준다.", response = Post.class)
	@GetMapping("searchAlgoSolved/{userId}")
	public ResponseEntity<List<String>> searchAlgoSolved(@PathVariable String userId) throws Exception {
		logger.debug("searchAlgoSolved - 호출");
		return new ResponseEntity<List<String>>(postService.searchAlgoSolved(userId), HttpStatus.OK);
	}
	
	@ApiOperation(value = "못 푼 문제를 알고리즘 페이지에서 보여준다.", response = Post.class)
	@GetMapping("searchAlgoUnSolved/{userId}")
	public ResponseEntity<List<String>> searchAlgoUnSolved(@PathVariable String userId) throws Exception {
		logger.debug("searchAlgoSolved - 호출");
		return new ResponseEntity<List<String>>(postService.searchAlgoUnSolved(userId), HttpStatus.OK);
	}
	
	@ApiOperation(value = "POST 입력 - SNS 그리고 DB입력 성공여부에 따라 '게시글의 no' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/insertSns")
	public ResponseEntity<String> insertSns(@RequestBody Post post) throws Exception {
		logger.debug("SNS 게시물 넣기");
		if (postService.insertSns(post)) {
			String result = postService.selectPostNumber(post)+"";
			return new ResponseEntity<String>(result, HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT );
	}
	
	@ApiOperation(value = "POST 입력 - CODE 그리고 DB입력 성공여부에 따라 '게시글의 no' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/insertCode")
	public ResponseEntity<String> insertCode(@RequestBody Post post) throws Exception {
		logger.debug("CODE 게시물 넣기");
		
		if (postService.insertCode(post)) {
			String result = postService.selectPostNumber(post)+"";
			return new ResponseEntity<String>(result, HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT );
	}
	
	@ApiOperation(value = "POST 수정 - SNS 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/updateSns")
	public ResponseEntity<String> updateSns(@RequestBody Post post) throws Exception {
		logger.debug("updateSns - 호출");
		if (postService.updateSns(post)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT );
	}
	
	@ApiOperation(value = "POST 수정 - CODE 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/updateCode")
	public ResponseEntity<String> updateCode(@RequestBody Post post) throws Exception {
		logger.debug("글 번호로 검색된 Code 게시물 - 호출");
		if (postService.updateCode(post)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT );
	}
	
	@ApiOperation(value = "POST 삭제 - 그리고 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/deletePost")
	public ResponseEntity<String> deletePost(@RequestBody int postNumber) throws Exception {
		logger.debug("글 번호로 검색된 Code 게시물 - 호출");
		if (postService.deletePost(postNumber)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT );
	}
	
	
	
	@ApiOperation(value = "(AWS)사진 경로 등록하기", response = String.class)
	@PostMapping("/insertPostImage")
	 public ResponseEntity<String> insertPostImage(@RequestBody PostImage postImage) throws Exception {
		if(imagePostService.insertPostImage(postImage)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	   }
	
	@ApiOperation(value = "(AWS)사용자가 올린 사진 경로 불러오기", response = String.class)
	@PostMapping("/selectPostImage/{postNumber}")
	 public ResponseEntity<String> selectPostImage(@PathVariable int postNumber) throws Exception {
	      return new ResponseEntity<String>(imagePostService.selectPostImage(postNumber), HttpStatus.OK);
	   }
	
//	@ApiOperation(value = "(AWS)사용자가 올린사진 경로 수정하기", response = String.class)
//	@PostMapping("/updatePostImage")
//	 public ResponseEntity<String> updatePostImage(@RequestBody PostImage postImage) throws Exception {
//		if(imagePostService.updatePostImage(postImage)) {
//			return new ResponseEntity<String>("success", HttpStatus.OK);
//		}
//			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
//	   }
	
	@ApiOperation(value = "(AWS)사용자가 올린 사진 경로 삭제하기", response = String.class)
	@PostMapping("/deletePostImage/{postNumber}")
	 public ResponseEntity<String> deletePostImage(@PathVariable int postNumber) throws Exception {
		if(imagePostService.deletePostImage(postNumber)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
//	@ApiOperation(value = "좋아요 기능 구현, 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
//	@PostMapping("/likePost/{postNumber}")
//	public ResponseEntity<String> likePost(@PathVariable int postNumber) throws Exception {
//		logger.debug("likePost - 호출");
//		if (postService.likePost(postNumber)) {
//			return new ResponseEntity<String>("success", HttpStatus.OK);
//		}
//		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT );
//	}
	
//	@ApiOperation(value = "좋아요 취소 구현, 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
//	@PostMapping("/unlikePost/{postNumber}")
//	public ResponseEntity<String> unlikePost(@PathVariable int postNumber) throws Exception {
//		logger.debug("unlikePost - 호출");
//		if (postService.unlikePost(postNumber)) {
//			return new ResponseEntity<String>("success", HttpStatus.OK);
//		}
//		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT );
//	}
	
	@ApiOperation(value = "로그인 되지않아도 메인페이지에서 Ranking 정보를 반환한다.", response = String.class)
	@PostMapping("/rankingtoday")
	public ResponseEntity<List<RankUser>> rankingtoday() throws Exception {
		logger.debug("ranking 정보 호출");
		List<RankUser> rank = postService.selectRankingtoday();
		return new ResponseEntity<List<RankUser>>(rank, HttpStatus.OK);
	}
	@ApiOperation(value = "로그인 되지않아도 메인페이지에서 Ranking 정보를 반환한다.", response = String.class)
	@PostMapping("/rankingTotal")
	public ResponseEntity<List<RankUser>> rankingWeek() throws Exception {
		logger.debug("ranking 정보 호출");
		List<RankUser> rank = postService.selectRankingTotal();
		return new ResponseEntity<List<RankUser>>(rank, HttpStatus.OK);
	}
	
	@ApiOperation(value = "이달의 랭킹 중 나는 몇 위일까요?", response = String.class)
	@PostMapping("/myRankingTotal/{userId}")
	public ResponseEntity<Integer> myRankingTotal(@PathVariable String userId) throws Exception {
		logger.debug("이달의 랭킹 중 나는 몇 위일까요?");
		int myRank = postService.myRankingTotal(userId);
		return new ResponseEntity<Integer>(myRank, HttpStatus.OK);
	}
	
	// 모내기
	@ApiOperation(value = "userId로 모내기 정보를 반환해준다.", response = Post.class)
	@GetMapping("searchAlgoCalendarChart/{userId}")
	public ResponseEntity<List<Post>> searchAlgoCalendarChart(@PathVariable String userId) throws Exception {
		logger.debug("userId로 모내기 정보를 반환해준다. - 호출");
		return new ResponseEntity<List<Post>>(postService.searchAlgoCalendarChart(userId), HttpStatus.OK);
	}
	
	// 내 마음속에 저장~>.0
	@ApiOperation(value = "즐겨찾기를 등록한다.", response = String.class)
	@PostMapping("/insertStarPost")
	public ResponseEntity<String> insertStarPost(@RequestBody Post post) throws Exception {
		logger.debug("즐찾 등록");
		if (postService.insertStarPost(post)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT );
	}
	
	// 내 마음속에 안 저장~>.0
		@ApiOperation(value = "즐겨찾기를 해제한다.", response = String.class)
		@PostMapping("/deleteStarPost")
		public ResponseEntity<String> deleteStarPost(@RequestBody Post post) throws Exception {
			logger.debug("즐찾 등록");
			if (postService.deleteStarPost(post)) {
				return new ResponseEntity<String>("success", HttpStatus.OK);
			}
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT );
		}
		// 내 마음속에  저장 확인~>.0
		@ApiOperation(value = "즐겨찾기 확인.", response = String.class)
		@PostMapping("/checkStarPost")
		public ResponseEntity<String> checkStarPost(@RequestBody Post post) throws Exception {
			logger.debug("즐찾 확인");
			if (postService.checkStarPost(post)) {
				return new ResponseEntity<String>("success", HttpStatus.OK);
			}
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT );
		}
	
	// 내가 저장한 게시물만 조회하기
	@ApiOperation(value = "즐겨찾기 게시물만 조회한다.", response = Post.class)
	@GetMapping("selectStarPost/{userId}")
	public ResponseEntity<List<Post>> selectStarPost(@PathVariable String userId) throws Exception {
		logger.debug("즐겨찾기 게시물만 조회한다. - 호출");
		return new ResponseEntity<List<Post>>(postService.selectStarPost(userId), HttpStatus.OK);
	}
}
