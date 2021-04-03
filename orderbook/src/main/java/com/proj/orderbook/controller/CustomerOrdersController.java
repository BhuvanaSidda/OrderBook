package com.proj.orderbook.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.orderbook.dto.OrderDetailsDto;
import com.proj.orderbook.service.CustomerOrdersService;

@RestController
@RequestMapping("/order")
public class CustomerOrdersController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerOrdersController.class);

	@Autowired
	CustomerOrdersService customerOrdersService;

	@PostMapping("/add")
	private String createOrder(@RequestBody OrderDetailsDto orderDetailsDto) {
		logger.info("::::Order Creation Controller:::");
		return customerOrdersService.createOrder(orderDetailsDto);
	}

}
