package com.dima.test.dto;
public class User {

    // USER_ID 
    private String userId;

    // USER_PW 
    private String userPw;

    // USER_NAME 
    private String userName;

    // USER_NICKNAME 
    private String userNickname;

    // USER_EMAIL 
    private String userEmail;

    // USER_GITURL 
    private String userGiturl;

    // USER_IMAGE 
    private String userImage;

    // USER_AUTHO 
    private String userAutho;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserGiturl() {
        return userGiturl;
    }

    public void setUserGiturl(String userGiturl) {
        this.userGiturl = userGiturl;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserAutho() {
        return userAutho;
    }

    public void setUserAutho(String userAutho) {
        this.userAutho = userAutho;
    }

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userPw=" + userPw + ", userName=" + userName + ", userNickname="
				+ userNickname + ", userEmail=" + userEmail + ", userGiturl=" + userGiturl + ", userImage=" + userImage
				+ ", userAutho=" + userAutho + "]";
	}
    
    
}