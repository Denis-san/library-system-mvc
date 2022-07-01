package br.com.san.ls.service;

import java.util.List;

import br.com.san.ls.entity.Book;

public interface BookService {

	public List<Book> search(String search);

	public List<Book> getAllBooks();
	
}
