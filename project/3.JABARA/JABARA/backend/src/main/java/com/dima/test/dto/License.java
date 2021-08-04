package com.dima.test.dto;

public class License {

    // LICENSE_NAME 
    private String licenseName;

    // LICENSE_NUMBER 
    private String licenseNumber;

    // LICENSE_DATE 
    private String licenseDate;

    // LICENSE_INST 
    private String licenseInst;

    // LICENSE_SCORE 
    private String licenseScore;

    // LICENSE_ETC 
    private String licenseEtc;

    // USER_ID 
    private String userId;

    public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getLicenseName() {
        return licenseName;
    }

    public void setLicenseName(String licenseName) {
        this.licenseName = licenseName;
    }


    public String getLicenseDate() {
        return licenseDate;
    }

    public void setLicenseDate(String licenseDate) {
        this.licenseDate = licenseDate;
    }

    public String getLicenseInst() {
        return licenseInst;
    }

    public void setLicenseInst(String licenseInst) {
        this.licenseInst = licenseInst;
    }

    public String getLicenseScore() {
        return licenseScore;
    }

    public void setLicenseScore(String licenseScore) {
        this.licenseScore = licenseScore;
    }

    public String getLicenseEtc() {
        return licenseEtc;
    }

    public void setLicenseEtc(String licenseEtc) {
        this.licenseEtc = licenseEtc;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    // License 모델 복사
    public void CopyData(License param)
    {
        this.licenseName = param.getLicenseName();
        this.licenseDate = param.getLicenseDate();
        this.licenseInst = param.getLicenseInst();
        this.licenseScore = param.getLicenseScore();
        this.licenseEtc = param.getLicenseEtc();
        this.userId = param.getUserId();
    }
}
