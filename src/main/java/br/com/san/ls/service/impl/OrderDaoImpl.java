package br.com.san.ls.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.san.ls.entity.Order;
import br.com.san.ls.entity.enums.OrderStatus;
import br.com.san.ls.service.OrderDao;

@Repository
@Transactional
public class OrderDaoImpl implements OrderDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void saveNewOrder(Order order) {
		entityManager.persist(order);
	}

	@Override
	public Order findById(Integer id) {
		Order order = entityManager.find(Order.class, id);
		return order;
	}

	@Override
	public void updateStatus(Integer id, OrderStatus status) {
		Query query = entityManager.createQuery("UPDATE Order o SET o.status =: status WHERE id =: id");
		query.setParameter("status", status);
		query.setParameter("id", id);

		query.executeUpdate();
	}

}
