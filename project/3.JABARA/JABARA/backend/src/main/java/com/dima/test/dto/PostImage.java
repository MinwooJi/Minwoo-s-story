package com.dima.test.dto;

public class PostImage {

    // POST_NUMBER 
    private Integer postNumber;

    // IMAGE_PATH 
    private String imagePath;

	public Integer getPostNumber() {
		return postNumber;
	}

	public void setPostNumber(Integer postNumber) {
		this.postNumber = postNumber;
	}

	public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}