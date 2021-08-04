package com.ssafy.happyhouse.model.service;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.model.dao.NoticeDao;
import com.ssafy.happyhouse.model.dao.NoticeDaoImpl;
import com.ssafy.happyhouse.model.dto.NoticeDto;
import com.ssafy.happyhouse.util.KMPalgorithm;
import com.ssafy.happyhouse.util.MergeSort;
import com.ssafy.happyhouse.util.PageNavigation;


public class NoticeServiceImpl implements NoticeService {
	
	private NoticeDao noticeDao;
	private KMPalgorithm KMP;
	private MergeSort mergeSort;
	
	public NoticeServiceImpl() {
		noticeDao = new NoticeDaoImpl();
		KMP = new KMPalgorithm();
		mergeSort = new MergeSort();
	}

	@Override
	public void writeNotice(NoticeDto noticeDto) throws Exception {
		if(noticeDto.getSubject() == null || noticeDto.getContent() == null) {
			throw new Exception();
		}
		noticeDao.writeNotice(noticeDto);
	}

	@Override
	public List<NoticeDto> listNotice(String key, String word) throws Exception {
		key = key == null ? "" : key;
		word = word == null ? "" : word;
		List<NoticeDto> temp = noticeDao.selectAll();
		List<NoticeDto> list = new ArrayList<>();
		NoticeDto[] arr = null;
		if("".equals(key) || "".equals(word)) {
			mergeSort.mergeSort(temp);
			return temp;
		}
		if("adminid".equals(key)) {
			for (NoticeDto noticeDto : temp) {
				if(KMP.KMP(noticeDto.getUserid(), word)) {
					list.add(noticeDto);
				}
			}
		}else if("noticeno".equals(key)) {
			for (NoticeDto noticeDto : temp) {
				if(KMP.KMP(Integer.toString(noticeDto.getNoticeno()), word)) {
					list.add(noticeDto);
				}
			}
		}else if("subject".equals(key)) {
			for (NoticeDto noticeDto : temp) {
				if(KMP.KMP(noticeDto.getSubject(), word)) {
					list.add(noticeDto);
				}
			}
		}
		mergeSort.mergeSort(list);
		for (NoticeDto noticeDto : list) {
			System.out.println(noticeDto);
		}
		return list;
	}

	@Override
	public NoticeDto getNotice(int noticeno) throws Exception {
		return noticeDao.getNotice(noticeno);
	}

	@Override
	public void modifyNotice(NoticeDto noticeDto) throws Exception {
		noticeDao.modifyNotice(noticeDto);
	}

	@Override
	public void deleteNotice(int noticeno) throws Exception {
		noticeDao.deleteNotice(noticeno);
	}

	@Override
	public PageNavigation makePageNavigation(int currentPage, int sizePerPage, String key, String word) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();
		int naviSize = 10; // 페이지 개수
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		int totalCount = noticeDao.getTotalCount(key, word); // 총게시글 수
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1)/ sizePerPage + 1;// 전체 페이지수
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize; // startRange true : 이전버튼 X false : 이전번트 O
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount -1)/naviSize * naviSize < currentPage;// endRange true : 나중버튼 X false : 나중번트 O
		pageNavigation.setEndRange(endRange); 
		pageNavigation.makeNavigator();
		return pageNavigation;
	}

	@Override
	public List<NoticeDto> listNotice(int currentPage, int sizePerPage, String key, String word) throws Exception {
		key = key == null ? "" : key;
		word = word == null ? "" : word;
		return noticeDao.listNotice(currentPage, sizePerPage, key, word);
	}

}
