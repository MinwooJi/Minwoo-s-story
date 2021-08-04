package com.dima.test.dto;

public class Tag {

    // TAG_NUMBER 
    private int tagNumber;

    // TAG_NAME 
    private String tagName;

    private int postNumber;
    
    private String tagStr;
    
    private String category;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getTagNumber() {
		return tagNumber;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public int getPostNumber() {
		return postNumber;
	}

	public void setPostNumber(int postNumber) {
		this.postNumber = postNumber;
	}

	public String getTagStr() {
		return tagStr;
	}

	public void setTagStr(String tagStr) {
		this.tagStr = tagStr;
	}

	public void setTagNumber(int tagNumber) {
		this.tagNumber = tagNumber;
	}

	@Override
	public String toString() {
		return "Tag [tagNumber=" + tagNumber + ", tagName=" + tagName + ", postNumber=" + postNumber + ", tagStr="
				+ tagStr + "]";
	}

	
}