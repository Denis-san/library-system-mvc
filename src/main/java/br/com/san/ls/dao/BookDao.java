package br.com.san.ls.dao;

import java.util.List;

import br.com.san.ls.entity.Book;

public interface BookDao {

	public List<Book> search(String search);

	public List<Book> allBooks();

	public void saveBook(Book book);

	public Book findBookById(Integer id);

	public void deleteById(Integer id);

	public Long quantityOfBookRecords();

}
