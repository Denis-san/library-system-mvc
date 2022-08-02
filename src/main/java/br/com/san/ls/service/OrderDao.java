package br.com.san.ls.service;

import br.com.san.ls.entity.Order;
import br.com.san.ls.entity.enums.OrderStatus;

public interface OrderDao {

	void saveNewOrder(Order order);

	Order findById(Integer id);

	void updateStatus(Integer id, OrderStatus status);

}
