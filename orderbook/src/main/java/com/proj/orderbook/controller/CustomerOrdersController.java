package com.proj.orderbook.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proj.orderbook.dto.OrderDetailsDto;
import com.proj.orderbook.dto.OrderDetailsResponseDto;
import com.proj.orderbook.service.CustomerOrdersService;

@RestController
@RequestMapping("/order")
public class CustomerOrdersController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerOrdersController.class);

	@Autowired
	CustomerOrdersService customerOrdersService;

	@PostMapping("/add")
	private String createOrder(@Valid @RequestBody OrderDetailsDto orderDetailsDto) {
		logger.info("::::Order Creation Controller:::");
		return customerOrdersService.createOrder(orderDetailsDto);
	}

	@GetMapping()
	private OrderDetailsResponseDto viewOrders(@RequestParam String customerId) {
		logger.info("::::Fetching Order details based on customerId Controller:::");
		return customerOrdersService.viewOrders(customerId);
	}
}
