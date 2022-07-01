package br.com.san.ls.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.san.ls.dao.BookDao;
import br.com.san.ls.entity.Book;

@Repository
public class BookDaoImpl implements BookDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Book> search(String search) {

		search = search.toLowerCase();
		TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b WHERE LOWER(b.title) LIKE :search ORDER BY b.title",
				Book.class);
		query.setParameter("search", "%" + search + "%");

		List<Book> result = query.getResultList();
		return result;

	}

	@Override
	public List<Book> allBooks() {
		TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b", Book.class);
		return query.getResultList();
	}

}
