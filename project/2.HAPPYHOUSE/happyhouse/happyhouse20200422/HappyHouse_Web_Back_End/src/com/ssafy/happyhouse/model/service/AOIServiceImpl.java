package com.ssafy.happyhouse.model.service;

import java.util.LinkedList;
import java.util.List;

import com.ssafy.happyhouse.HappyHouseException;
import com.ssafy.happyhouse.model.dao.AreaOfInterestDao;
import com.ssafy.happyhouse.model.dao.AreaOfInterestDaoImpl;
import com.ssafy.happyhouse.model.dto.Member;
import com.ssafy.happyhouse.model.dto.Region;

public class AOIServiceImpl implements AOIService {
	private AreaOfInterestDao dao;
	public AOIServiceImpl() {
		dao = new AreaOfInterestDaoImpl();
	}
	@Override
	public List<Region> searchAll(Member member) {
		List<Region> list = new LinkedList<Region>();
		try {
			list = dao.searchAOI(member);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HappyHouseException("관심정보 입력중 오류 발생");
		}
		if(list == null) {
			System.out.println("해당아이디가 등록한 관심지역이 없습니다");
		}
		return list;
	}

	@Override
	public void insertAOI(Member member,Region region) {
		try {
			dao.insertAOI(member, region);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HappyHouseException("관심정보 입력중 오류 발생");
		}

	}

	@Override
	public void deleteAOI(Member member,Region region) {
		try {
			dao.deleteAOI(member, region);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HappyHouseException("관심정보 입력중 오류 발생");
		}

	}

	public static void main(String[] args) {
		AOIServiceImpl aoiImpl = new AOIServiceImpl();
		List<Region> list;
		Member member = new Member("asdf","1234","지민우","무거동","pum","address_detail","post","homeNum");
		Region region = new Region("333","서울","세종시","아름동");
		System.out.println("-------- id = asdf, code 333 정보 넣기 전 --------");
		list = aoiImpl.searchAll(member);
		for (Region region2 : list) {
			System.out.println(region2);
		}
		System.out.println("-------- id = asdf, code 333 정보 넣은 후 --------");
		aoiImpl.insertAOI(member, region);
		list.clear();
		list = aoiImpl.searchAll(member);
		for (Region region2 : list) {
			System.out.println(region2);
		}
		System.out.println("-------- id = asdf, code 333 정보 삭제 후 --------");
		aoiImpl.deleteAOI(member, region);
		list.clear();
		list = aoiImpl.searchAll(member);
		for (Region region2 : list) {
			System.out.println(region2);
		}
		
	}

}
