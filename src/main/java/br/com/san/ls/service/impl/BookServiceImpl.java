package br.com.san.ls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.san.ls.dao.BookDao;
import br.com.san.ls.entity.Book;
import br.com.san.ls.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookDao bookDao;

	@Override
	public List<Book> search(String search) {
		return bookDao.search(search);
	}
	
	
}
