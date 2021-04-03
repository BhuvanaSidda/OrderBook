package com.proj.orderbook.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proj.orderbook.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	@PutMapping
	private String processingOrders(@RequestParam Integer quantity, @RequestParam Double price,
			@RequestParam Integer stockId, @RequestParam LocalDate orderDate) {
		return adminService.processOrder(quantity, price, stockId, orderDate);

	}

}
