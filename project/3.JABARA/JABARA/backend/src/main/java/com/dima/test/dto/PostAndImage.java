package com.dima.test.dto;

public class PostAndImage {
	// POST_NUMBER
	private int postNumber;
	// POST_TITLE
	private String postTitle;

	// POST_SNS_CONTENT
	private String postSnsContent;

	// POST_CODE_CONTENT
	private String postCodeContent;

	// POST_LIKE
	private int postLike;
	// POST_CATEGORY
	private String postCategory;

	private String postDate;


	public int getPostNumber() {
		return postNumber;
	}

	public void setPostNumber(int postNumber) {
		this.postNumber = postNumber;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
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

	public int getPostLike() {
		return postLike;
	}

	public void setPostLike(int postLike) {
		this.postLike = postLike;
	}

	public String getPostCategory() {
		return postCategory;
	}

	public void setPostCategory(String postCategory) {
		this.postCategory = postCategory;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
}
