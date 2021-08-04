package com.dima.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dima.test.dao.ProfileExpDao;
import com.dima.test.dto.Exp;
import com.dima.test.dto.License;
import com.dima.test.dto.ResumeDetail;

@Service
public class ProfileExpServiceImpl implements ProfileExpService {

	@Autowired
	ProfileExpDao profiledao;
	@Override
	public boolean insertExp(Exp exp) {
		return profiledao.insertExp(exp)==1;
	}

	@Override
	public boolean insertLicense(License license) {
		return profiledao.insertLicense(license)==1;
	}

	@Override
	public int selectExpCount(String userId) {
		return profiledao.selectExpCount(userId);
	}

	@Override
	public int selectExpMax(String userId) {
		return profiledao.selectExpMax(userId);
	}
	
	@Override
	public int selectLicenseCount(String userId) {
		return profiledao.selectLicenseCount(userId);
	}

	@Override
	public List<Exp> selectExpList(String userId) {
		return profiledao.selectExpList(userId);
	}

	@Override
	public List<License> selectLicenseList(String userId) {
		return profiledao.selectLicenseList(userId);
	}

	@Override
	public void modifyExp(Exp exp) {
		profiledao.updateExp(exp);
	}

	@Override
	public void modifyLicense(License license) {
		profiledao.updateLicense(license);
	}

	@Override
	public void deleteExp(Exp exp) {
		profiledao.deleteExp(exp);
	}

	@Override
	public void deleteLicense(License license) {
		profiledao.deleteLicense(license);
	}

	@Override
	public List<ResumeDetail> selectExpByQ(String[] keyword) {
		return profiledao.selectExpByQ(keyword);
	}



}
