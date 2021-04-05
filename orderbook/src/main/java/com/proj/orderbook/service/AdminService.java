package com.proj.orderbook.service;

import com.proj.orderbook.dto.OrderDetailsByStock;

public interface AdminService {

	String processOrder(Integer quantity, Double price, Integer stockId, String orderDate);

	String changeStatus(String adminId, String status);

	OrderDetailsByStock fetchOrdersByStock(Integer stockId, String fromDate, String toDate);

	String executeOrders(Integer quantity, Double price, Integer stockId, String orderDate);

}
