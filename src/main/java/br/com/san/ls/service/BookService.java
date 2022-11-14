package br.com.san.ls.service;

import java.util.List;
import java.util.Set;

import br.com.san.ls.entity.Author;
import br.com.san.ls.entity.Book;

public interface BookService {

	public List<Book> search(String search);

	public List<Book> getAllBooks();

	public void saveNewBook(Book book);

	public Book getBookById(Integer id);

	public void deleteBookById(Integer id);

	public Long getQuantityOfBookRecords();

	public Set<Author> getAllAuthorsFromSearchResult(List<Book> results);

}
