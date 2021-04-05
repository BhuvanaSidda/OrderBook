package com.proj.orderbook.controller;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proj.orderbook.dto.OrderDetailsByStock;
import com.proj.orderbook.service.AdminService;

@RestController
@RequestMapping("/market")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	AdminService adminService;

	@PutMapping
	private String processingOrders(@RequestParam Integer quantity, @RequestParam Double price,
			@RequestParam Integer stockId, @RequestParam String orderDate) {
		logger.info("::::Processing Orders Controller:::::");
		return adminService.processOrder(quantity, price, stockId, orderDate);

	}

	@PutMapping("/statusChange")
	private String changeMarketStatus(@RequestParam String adminId, @RequestParam String status) {
		logger.info(":::::Change market Status controller::::");
		return adminService.changeStatus(adminId, status);
	}

	@GetMapping()
	private OrderDetailsByStock fetchOrdersByStock(@RequestParam Integer stockId, @RequestParam String fromDate,
			@RequestParam String toDate) {
		logger.info(":::::Fetching orders by stock id controller::::");
		return adminService.fetchOrdersByStock(stockId, fromDate, toDate);
	}
	
	@PutMapping("execute")
	private String executeOrders(@RequestParam Integer quantity, @RequestParam Double price,
			@RequestParam Integer stockId, @RequestParam String orderDate) {
		logger.info("::::Processing Orders Controller:::::");
		return adminService.executeOrders(quantity, price, stockId, orderDate);

	}

}
