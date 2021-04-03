package com.proj.orderbook.service;

import java.time.LocalDate;

public interface AdminService {

	String processOrder(Integer quantity, Double price, Integer stockId, LocalDate orderDate);

	String changeStatus(String adminId, String status);

}
