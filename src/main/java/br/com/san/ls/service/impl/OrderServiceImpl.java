package br.com.san.ls.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.san.ls.entity.Order;
import br.com.san.ls.entity.enums.OrderStatus;
import br.com.san.ls.service.OrderDao;
import br.com.san.ls.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	@Override
	public void saveNewOrder(Order order) {

		// set random code

		if (order.getId() == null) {
			order.setOrderStatus(OrderStatus.REQUESTED);
			orderDao.saveNewOrder(order);
		}

	}

	@Override
	public Order findOrderById(Integer id) {
		return orderDao.findById(id);
		
	}

	@Override
	public void updadeStatus(Integer id, OrderStatus status) {
		orderDao.updateStatus(id, status);
		
	}
	
	

}
