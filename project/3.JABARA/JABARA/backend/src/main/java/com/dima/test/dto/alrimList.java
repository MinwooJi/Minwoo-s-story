package com.dima.test.dto;

import java.sql.Timestamp;
import java.util.Date;

public class alrimList {
	private String content;
	private Timestamp date;
	private int postNumber;
	private String userId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getPostNumber() {
		return postNumber;
	}
	public void setPostNumber(int postNumber) {
		this.postNumber = postNumber;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date2) {
		this.date = date2;
	}
	
}
