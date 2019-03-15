package com.allan.tacos.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.allan.tacos.model.Order;
import com.allan.tacos.model.User;
import com.allan.tacos.props.OrderProps;
import com.allan.tacos.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderProps orderProps;
	
 
	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}

	@GetMapping("/current")
	public String orderForm(Model model) {
		return "order/orderForm";
	}
	
	@GetMapping
	public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
		log.info("ordersForUser() {}", user);
		model.addAttribute("orders", this.orderRepository.findByUserOrderByPlacedAtDesc(user, PageRequest.of(0, orderProps.getPageSize())));
		return "order/orderList";
	}
	
	@PostMapping
	public String processForm(@Valid Order order, Errors errors, 
					SessionStatus sessionStatus,
					@AuthenticationPrincipal User user) {
		if(errors.hasErrors()) {
			return "order/orderForm";
		}
		
		log.info("Order submitted: " + order);
		
		order.setUser(user);
		orderRepository.save(order);
		sessionStatus.setComplete();
		
		return "redirect:/";
	}
}
