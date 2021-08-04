package com.dima.test.dto;

import java.sql.Timestamp;

public class Post {

	    // POST_NUMBER 
	    private Integer postNumber;

	    // USER_ID 
	    private String userId;

	    // POST_SNS_CONTENT 
	    private String postSnsContent;

	    // POST_CODE_CONTENT 
	    private String postCodeContent;

	    // POST_LIKE 
	    private Integer postLike;

	    // POST_CATEGORY 
	    private String postCategory;

	    // POST_DATE 
	    private Timestamp postDate;

	    // IS_SOLVED 
	    private Integer isSolved;

	    // POST_COMMENT_COUNT 
	    private Integer postCommentCount;

	    // POST_ALGO_TITLE 
	    private String postAlgoTitle;
	    
	    private String imagePath;

	    public String getImagePath() {
			return imagePath;
		}
	    //모내기용
	    public int countAlgoCal;

		public int getCountAlgoCal() {
			return countAlgoCal;
		}

		public void setCountAlgoCal(int countAlgoCal) {
			this.countAlgoCal = countAlgoCal;
		}

		public void setImagePath(String imagePath) {
			this.imagePath = imagePath;
		}

		public Integer getPostNumber() {
	        return postNumber;
	    }

	    public void setPostNumber(Integer postNumber) {
	        this.postNumber = postNumber;
	    }

	    public String getUserId() {
	        return userId;
	    }

	    public void setUserId(String userId) {
	        this.userId = userId;
	    }

	    public String getPostSnsContent() {
	        return postSnsContent;
	    }

	    public void setPostSnsContent(String postSnsContent) {
	        this.postSnsContent = postSnsContent;
	    }

	    public String getPostCodeContent() {
	        return postCodeContent;
	    }

	    public void setPostCodeContent(String postCodeContent) {
	        this.postCodeContent = postCodeContent;
	    }

	    public Integer getPostLike() {
	        return postLike;
	    }

	    public void setPostLike(Integer postLike) {
	        this.postLike = postLike;
	    }

	    public String getPostCategory() {
	        return postCategory;
	    }

	    public void setPostCategory(String postCategory) {
	        this.postCategory = postCategory;
	    }

	    public Timestamp getPostDate() {
	        return postDate;
	    }

	    public void setPostDate(Timestamp postDate) {
	        this.postDate = postDate;
	    }

	    public Integer getIsSolved() {
	        return isSolved;
	    }

	    public void setIsSolved(Integer isSolved) {
	        this.isSolved = isSolved;
	    }

	    public Integer getPostCommentCount() {
	        return postCommentCount;
	    }

	    public void setPostCommentCount(Integer postCommentCount) {
	        this.postCommentCount = postCommentCount;
	    }

	    public String getPostAlgoTitle() {
	        return postAlgoTitle;
	    }

	    public void setPostAlgoTitle(String postAlgoTitle) {
	        this.postAlgoTitle = postAlgoTitle;
	    }

}