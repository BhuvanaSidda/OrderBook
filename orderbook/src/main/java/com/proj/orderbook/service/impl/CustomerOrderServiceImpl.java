package com.proj.orderbook.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.orderbook.dto.OrderDetailsDto;
import com.proj.orderbook.dto.OrderDetailsResponseDto;
import com.proj.orderbook.dto.StockDetailsDto;
import com.proj.orderbook.entity.CustomerOrderDetails;
import com.proj.orderbook.entity.MarketStatus;
import com.proj.orderbook.entity.StockDetails;
import com.proj.orderbook.entity.UserMaster;
import com.proj.orderbook.exceptions.CommonException;
import com.proj.orderbook.repository.CustomerOrderDetailsRepository;
import com.proj.orderbook.repository.MarketStatusRepository;
import com.proj.orderbook.repository.StockDetailsRepository;
import com.proj.orderbook.repository.UserMasterRepository;
import com.proj.orderbook.service.CustomerOrdersService;

@Service
public class CustomerOrderServiceImpl implements CustomerOrdersService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerOrderServiceImpl.class);

	String customerType = "C";
	public static String marketStatus = "Open";

	@Autowired
	UserMasterRepository userMasterRepository;

	@Autowired
	StockDetailsRepository stockDetailsRepository;

	@Autowired
	MarketStatusRepository marketStatusRepository;

	@Autowired
	CustomerOrderDetailsRepository customerOrdersRepository;

	@Override
	public String createOrder(OrderDetailsDto orderDetailsDto) {
		MarketStatus marketStatus = marketStatusRepository.findByUserIdAndMarketStatus("admin", "Open");
		if (Objects.isNull(marketStatus)) {
			return "Market is closed , please try when market is opened";
		}
		try {
			UserMaster userDetails = userMasterRepository.findByUserIdAndUserType(orderDetailsDto.getCustomerId(), "C");
			StockDetails stockDetails = stockDetailsRepository.findById(orderDetailsDto.getStockId()).get();
			CustomerOrderDetails customerOrders = new CustomerOrderDetails();
			BeanUtils.copyProperties(orderDetailsDto, customerOrders);
			logger.info("orderQuantity:" + orderDetailsDto.getOrderQuantity() + " " + customerOrders.getQuantity());
			customerOrders.setStockName(stockDetails.getStockName());
			customerOrders.setOrderStatus("Placed");
			customerOrders.setQuantity(orderDetailsDto.getOrderQuantity());
			customerOrders.setOrderDate(LocalDate.now());
			customerOrders.setUserName(userDetails.getUserName());
			customerOrders.setUserId(orderDetailsDto.getCustomerId());
			customerOrdersRepository.save(customerOrders);

		} catch (Exception e) {
			e.printStackTrace();
			throw new CommonException("Please try after sometime", LocalDateTime.now());

		}
		return "Order details plcaed successfully";

	}

	@Override
	public OrderDetailsResponseDto viewOrders(String customerId) {
		OrderDetailsResponseDto orderDetailsResponseDto = new OrderDetailsResponseDto();
		try {
			List<CustomerOrderDetails> customerOrdersList = customerOrdersRepository.findByUserId(customerId);

			orderDetailsResponseDto.setCustomerId(customerId);
			List<StockDetailsDto> stockDetailsList = new ArrayList<>();
			for (CustomerOrderDetails customerOrders : customerOrdersList) {
				orderDetailsResponseDto.setCustomerName(customerOrders.getUserName());
				StockDetailsDto stockDetails = new StockDetailsDto();
				BeanUtils.copyProperties(customerOrders, stockDetails);
				stockDetails.setOrderDate(customerOrders.getOrderDate());
				stockDetails.setOrderQuantity(customerOrders.getQuantity());
				stockDetailsList.add(stockDetails);
			}
			orderDetailsResponseDto.setStockDetails(stockDetailsList);

		} catch (Exception e) {
			throw new CommonException("Please try after sometime", LocalDateTime.now());
		}

		return orderDetailsResponseDto;
	}

}
