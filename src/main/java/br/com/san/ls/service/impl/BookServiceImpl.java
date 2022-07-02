package br.com.san.ls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.san.ls.dao.BookDao;
import br.com.san.ls.entity.Book;
import br.com.san.ls.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;

	@Override
	public List<Book> search(String search) {
		return bookDao.search(search);
	}

	@Override
	public void saveNewBook(Book book) {
		bookDao.saveBook(book);
	}

	@Override
	public List<Book> getAllBooks() {

		List<Book> results = bookDao.allBooks();

		return results;
	}

	@Override
	public Book getBookById(Integer id) {
		return bookDao.findBookById(id);
	}

	@Override
	public void deleteBookById(Integer id) {
		bookDao.deleteById(id);
	}

	@Override
	public Long getQuantityOfBookRecords() {
		Long result = bookDao.quantityOfBookRecords();
		return result;
	}

}
