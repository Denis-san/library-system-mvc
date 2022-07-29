package br.com.san.ls.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/review")
	public String orderIndex(HttpSession session) {

		if (session.getAttribute("cart") == null) {
			return "redirect:/";
		} else {
			Order cart = (Order) session.getAttribute("cart");
			if (cart.getItems().isEmpty()) {
				return "redirect:/";
			}
		}

		return "pages/order/order_details";
	}

	@RequestMapping("/cart/lend/{id}")
	public String orderDetails(@PathVariable(required = false, name = "id") Integer id, HttpSession session) {

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

		return "redirect:/order/review";
	}

	@RequestMapping("/cart/remove/{id}")
	public String removeOrderItem(@PathVariable(required = true, name = "id") Integer id, HttpSession session) {

		Order cart = (Order) session.getAttribute("cart");
		OrderItem item = cart.getItems().stream().filter(e -> e.getBook().getId() == id).findAny().orElse(null);

		cart.getItems().remove(item);

		session.setAttribute("cart", cart);
		return "redirect:/order/review";
	}

	private boolean itemExists(Order order, Integer id) {
		for (OrderItem item : order.getItems()) {
			if (item.getBook().getId() == id) {
				return true;
			}
		}

		return false;
	}
}
