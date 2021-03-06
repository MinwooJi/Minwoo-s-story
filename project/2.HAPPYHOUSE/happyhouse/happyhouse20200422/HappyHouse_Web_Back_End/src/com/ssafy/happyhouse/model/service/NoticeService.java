package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.NoticeDto;
import com.ssafy.happyhouse.util.PageNavigation;

public interface NoticeService {

	public void writeNotice(NoticeDto noticeDto) throws Exception;
	public List<NoticeDto> listNotice(String key, String word) throws Exception;
	public List<NoticeDto> listNotice(int pg, int sizePerPage, String key, String word) throws Exception;
	public PageNavigation makePageNavigation(int pg, int sizePerPage, String key, String word) throws Exception;
	
	public NoticeDto getNotice(int noticeno) throws Exception;
	public void modifyNotice(NoticeDto noticeDto) throws Exception;
	public void deleteNotice(int noticeno) throws Exception;
	
}
