package br.com.san.ls.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.san.ls.entity.Order;
import br.com.san.ls.entity.OrderItem;
import br.com.san.ls.service.BookService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private BookService bookService;

	@RequestMapping("/cart/lend/{id}")
	public String orderDetails(@PathVariable(required = true, name = "id") Integer id, HttpSession session) {

		OrderItem item = new OrderItem(bookService.getBookById(id));

		if (session.getAttribute("cart") == null) {
			Order cart = new Order();
			cart.getItems().add(item);
			session.setAttribute("cart", cart);
		} else {
			Order cart = (Order) session.getAttribute("cart");
			if (itemExists(cart, id) == false) {
				cart.getItems().add(item);
			}
			session.setAttribute("cart", cart);
		}

		return "pages/order/order_details";
	}

	private boolean itemExists(Order order, Integer id) {
		for (OrderItem item : order.getItems()) {
			return (item.getId() == id);
		}

		return false;
	}
}
