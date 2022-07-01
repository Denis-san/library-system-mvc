package br.com.san.ls.dao;

import java.util.List;

import br.com.san.ls.entity.Book;

public interface BookDao {
	
	public List<Book> search(String search);

	public List<Book> allBooks();
	
}
