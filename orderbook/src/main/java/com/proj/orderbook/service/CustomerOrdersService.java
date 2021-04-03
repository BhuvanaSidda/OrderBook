package com.proj.orderbook.service;

import com.proj.orderbook.dto.OrderDetailsDto;

public interface CustomerOrdersService {

	String createOrder(OrderDetailsDto orderDetailsDto);

}
