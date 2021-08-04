package com.dima.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dima.test.dto.Exp;
import com.dima.test.dto.License;
import com.dima.test.dto.ResumeDetail;

public interface ProfileExpService {
	boolean insertExp(Exp exp);
	boolean insertLicense(License license);
	int selectExpCount(String userId);
	int selectExpMax(String userId);
	int selectLicenseCount(String userId);
	List<Exp> selectExpList(String userId);
	List<License> selectLicenseList(String userId);
	void modifyExp(Exp expNumber);
	void modifyLicense(License license);
	void deleteExp(Exp exp);
	void deleteLicense(License license);
	List<ResumeDetail> selectExpByQ(String[] keyword);
}
