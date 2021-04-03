package com.proj.orderbook.service.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.proj.orderbook.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Override
	public String processOrder(Integer quantity, Double price, Integer stockId, LocalDate orderDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
