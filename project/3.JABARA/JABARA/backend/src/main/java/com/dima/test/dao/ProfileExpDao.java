package com.dima.test.dao;

import java.util.List;

import com.dima.test.dto.Exp;
import com.dima.test.dto.License;
import com.dima.test.dto.ResumeDetail;

public interface ProfileExpDao {

	int insertExp(Exp exp);

	int insertLicense(License license);

	int selectExpCount(String userId);

	int selectExpMax(String userId);
	
	int selectLicenseCount(String userId);

	List<Exp> selectExpList(String userId);

	List<License> selectLicenseList(String userId);

	void updateExp(Exp expNumber);

	void updateLicense(License license);

	void deleteExp(Exp exp);

	void deleteLicense(License license);
	
	List<ResumeDetail> selectExpByQ(String[] keyword);

}
