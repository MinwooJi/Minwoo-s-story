package com.ssafy.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ssafy.test.dto.GuestBook;
import com.ssafy.test.service.GuestBookService;

@Controller
public class GuestBookController {
	@Autowired
	private GuestBookService service;
	
	// 전체 목록 가져오는 기능 구현 - 메서드명 retrieveGuestBook
	// 이동할 페이지는 /WEB-INF/views/guestbook/list.jsp 이동
	// 호출할 Path : /프로젝트패스/guestbook/list
	@GetMapping("/guestbook/list")
	public void retrieveGuestBook(Model model) {
		model.addAttribute("list", service.retrieveGuestBook());
	}
	
	@GetMapping("/guestbook/detail")
	public void detailGuestBook(Model model, int articleno) {
		model.addAttribute("guestbook", service.detailGuestBook(articleno));
	}

	// 등록폼	
	@GetMapping("/guestbook/writeform")	
	public void writeFormGuestBook() {}
	
	@PostMapping("/guestbook/write")
	public String writeGuestBook(GuestBook guestBook) {
		service.writeGuestBook(guestBook);
		return "redirect:/guestbook/list";
	}
	
	@GetMapping("/guestbook/modifyform")	
	public void modifyFormGuestBook(int articleno, Model model) {
		model.addAttribute("guestbook", service.detailGuestBook(articleno));
	}
	
	@PostMapping("/guestbook/modify")	
	public String modifyGuestBook(GuestBook guestbook) {
		service.modifyGuestBook(guestbook);
		return "redirect:/guestbook/list";
	}
	
	@GetMapping("/guestbook/delete")
	public String deleteGuestBook(int articleno) {
		service.deleteGuestBook(articleno);
		return "redirect:/guestbook/list";
	}
	
	
}




