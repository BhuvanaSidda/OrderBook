package com.proj.orderbook.service;

import com.proj.orderbook.dto.OrderDetailsDto;
import com.proj.orderbook.dto.OrderDetailsResponseDto;

public interface CustomerOrdersService {

	String createOrder(OrderDetailsDto orderDetailsDto);

	OrderDetailsResponseDto viewOrders(String customerId);

}
