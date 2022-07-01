package br.com.san.ls.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.san.ls.entity.Book;
import br.com.san.ls.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping("/search")
	public ModelAndView searchBook(@RequestParam(name = "search") String search) {
		ModelAndView mv = new ModelAndView("/pages/book/search");

		List<Book> results;

		if (search.isBlank()) {
			results = bookService.getAllBooks();
		} else {
			results = bookService.search(search);
		}

		mv.addObject("listBook", results);
		mv.addObject("search", search);
		return mv;
	}

}
