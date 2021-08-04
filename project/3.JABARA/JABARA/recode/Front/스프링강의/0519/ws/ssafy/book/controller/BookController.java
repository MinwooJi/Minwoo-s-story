package com.ssafy.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ssafy.book.dto.Book;
import com.ssafy.book.service.BookService;

@Controller
public class BookController {
	@Autowired 
	BookService  bookService;
	
	@GetMapping("/listBook.do")
	public String bookList(Model model) {
		model.addAttribute("list", bookService.searchAll());
		return "book/listBook";
	}
	
	@GetMapping("/searchBook.do")
	public String searchBook(String isbn, Model model) {
		model.addAttribute("book", bookService.search(isbn));
		return "book/searchBook";
	}
	
	@GetMapping("/insertBookForm.do")
	public String insertBookForm() {
		return "book/insertBook";
	}
	
	@PostMapping("/insertBook.do")
	public String insertBook(Book book) {
		bookService.insert(book);
		return "redirect:searchBook.do?isbn="+book.getIsbn();
	}
	@GetMapping("/updateBookForm.do")
	public String insertBookForm(String isbn, Model model) {
		model.addAttribute("book", bookService.search(isbn));
		return "book/updateBook";
	}
	
	@PostMapping("/updateBook.do")
	public String updateBook(Book book) {
		 bookService.update(book);
		return "redirect:searchBook.do?isbn="+book.getIsbn();
	}
	
	@GetMapping("/removeBook.do")
	public String removeBook(String isbn) {
		bookService.delete(isbn);
		return "redirect:listBook.do";
	}
}









