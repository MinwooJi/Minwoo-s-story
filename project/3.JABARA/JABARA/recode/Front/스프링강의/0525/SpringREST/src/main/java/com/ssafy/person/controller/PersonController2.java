package com.ssafy.person.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.person.dto.Person;
import com.ssafy.person.service.PersonService;

@RestController //나 컨트롤러!! ---> servlet-context.xml등록
@RequestMapping("/rest")
public class PersonController2 {
	
	@Autowired
	PersonService service;

	@RequestMapping("/list")
	public List<Person> list(Model m) {		
		return service.findAll();//JSP페이지 포워딩
	}
	
	
	/*
	@RequestMapping(value = "/form", method = RequestMethod.GET) //입력폼보기	
	public String form() {
	  return "person/inputForm";	
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST) //DB입력
	public String formInsert(Person vo) {
		service.registry(vo);
		return "redirect:/list";	
	}
	
	

	
	@RequestMapping(value = "/upform" , method = RequestMethod.GET)//수정폼 보이기
	public String upform(int no, Model m) {
		m.addAttribute("person",service.find(no));
		return "person/editForm";
	}
	
	@RequestMapping(value = "/upform" , method = RequestMethod.POST)//DB수정하기
	public String update(Person vo) {
		service.modify(vo);		
		return "redirect:/list";
	}
	
	@RequestMapping("/delete")//DB삭제하기
	public String delete(int no) {
		service.remove(no);
		return "redirect:/list";
	}
	
	*/
	
}








