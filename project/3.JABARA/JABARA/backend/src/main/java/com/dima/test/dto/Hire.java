package com.dima.test.dto;

import java.sql.Timestamp;

public class Hire {

	    // HIRE_NUMBER 
	    private int hireNumber;

	    // COMPANY_NAME 
	    private String companyName;

	    // START_DATE 
	    private Timestamp startDate;

	    // END_DATE 
	    private Timestamp endDate;

	    // COMPANY_URL 
	    private String companyUrl;

	    // COMPANY_IMAGE 
	    private String companyImage;
	    
	    private String userId;
	    
	    private int myApplyNumber;
	    
	    private int myCalNumber;

	    public int getMyCalNumber() {
			return myCalNumber;
		}

		public void setMyCalNumber(int myCalNumber) {
			this.myCalNumber = myCalNumber;
		}

		public int getMyApplyNumber() {
			return myApplyNumber;
		}

		public void setMyApplyNumber(int myApplyNumber) {
			this.myApplyNumber = myApplyNumber;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public int getHireNumber() {
	        return hireNumber;
	    }

	    public void setHireNumber(int hireNumber) {
	        this.hireNumber = hireNumber;
	    }

	    public String getCompanyName() {
	        return companyName;
	    }

	    public void setCompanyName(String companyName) {
	        this.companyName = companyName;
	    }

	    public Timestamp getStartDate() {
	        return startDate;
	    }

	    public void setStartDate(Timestamp startDate) {
	        this.startDate = startDate;
	    }

	    public Timestamp getEndDate() {
	        return endDate;
	    }

	    public void setEndDate(Timestamp endDate) {
	        this.endDate = endDate;
	    }

	    public String getCompanyUrl() {
	        return companyUrl;
	    }

	    public void setCompanyUrl(String companyUrl) {
	        this.companyUrl = companyUrl;
	    }

	    public String getCompanyImage() {
	        return companyImage;
	    }

	    public void setCompanyImage(String companyImage) {
	        this.companyImage = companyImage;
	    }
	    
}