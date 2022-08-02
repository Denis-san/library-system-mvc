package br.com.san.ls.service;

import br.com.san.ls.entity.Order;
import br.com.san.ls.entity.enums.OrderStatus;

public interface OrderService {

	void saveNewOrder(Order order);

	Order findOrderById(Integer id);

	void updadeStatus(Integer id, OrderStatus status);

}
