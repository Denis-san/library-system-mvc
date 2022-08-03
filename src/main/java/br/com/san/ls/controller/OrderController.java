package br.com.san.ls.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.san.ls.entity.Order;
import br.com.san.ls.entity.OrderItem;
import br.com.san.ls.entity.User;
import br.com.san.ls.entity.enums.OrderStatus;
import br.com.san.ls.service.BookService;
import br.com.san.ls.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private BookService bookService;

	@Autowired
	private OrderService orderService;

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

	@GetMapping("/processOrder")
	public String processOrder(HttpSession session) {

		if (session.getAttribute("cart") == null) {
			return "pages/error";
		}

		Order order = (Order) session.getAttribute("cart");

		if (order.getItems().isEmpty()) {
			return "pages/error";
		}

		// get user logged to set in the order

		User user = new User(1, null, null, null, null);
		order.setClient(user);

		orderService.saveNewOrder(order);

		return "redirect:/order/success";
	}

	@GetMapping("/success")
	public ModelAndView orderFinish(HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:/order/review");

		if (session.getAttribute("cart") != null) {
			Integer id = ((Order) session.getAttribute("cart")).getId();

			if (id != null) {

				Order order = orderService.findOrderById(id);

				if (order.getOrderStatus() == OrderStatus.REQUESTED) {
					orderService.updadeStatus(id, OrderStatus.FINISHED);
				}

				mv.setViewName("/pages/order/order_confirmation");
				session.removeAttribute("cart");

				mv.addObject("order", order);
			}
		}

		return mv;
	}

	@GetMapping("/status/{id}")
	public ModelAndView orderStatus(@PathVariable(required = true, name = "id") Integer id) {
		ModelAndView mv = new ModelAndView("/pages/error");

		if (id != null) {

			Order order = orderService.findOrderById(id);

			if (!order.getItems().isEmpty()) {
				mv.addObject("order", order);
				mv.setViewName("/pages/order/order_status");
			}
		}

		return mv;
	}

	@GetMapping("/cancel")
	public String cancelOrder(HttpSession session) {

		session.removeAttribute("cart");

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
