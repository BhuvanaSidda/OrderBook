package com.proj.orderbook.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.orderbook.dto.CustomerOrdersDto;
import com.proj.orderbook.dto.OrderDetailsByStock;
import com.proj.orderbook.entity.CustomerAcceptedOrders;
import com.proj.orderbook.entity.CustomerOrderDetails;
import com.proj.orderbook.entity.MarketStatus;
import com.proj.orderbook.entity.UserMaster;
import com.proj.orderbook.exceptions.CommonException;
import com.proj.orderbook.exceptions.OrdersNotFoundException;
import com.proj.orderbook.repository.CustomerAcceptedOrdersRepository;
import com.proj.orderbook.repository.CustomerOrderDetailsRepository;
import com.proj.orderbook.repository.MarketStatusRepository;
import com.proj.orderbook.repository.UserMasterRepository;
import com.proj.orderbook.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Autowired
	UserMasterRepository userMasterRepository;

	@Autowired
	MarketStatusRepository marketStatusRepository;

	@Autowired
	CustomerOrderDetailsRepository customerOrdersRepository;

	@Autowired
	CustomerAcceptedOrdersRepository customerAcceptedOrdersRepository;

	@Override
	public String processOrder(Integer quantity, Double price, Integer stockId, String orderDate) {
		logger.info(":::::Processesing orders Service::::");
		try {
			MarketStatus marketStatus = marketStatusRepository.findByUserIdAndMarketStatus("admin", "Close");
			if (Objects.isNull(marketStatus)) {
				return "Market opened, Please Close the market for processing orders";
			}

			List<CustomerOrderDetails> customerOrdersList = customerOrdersRepository
					.findByStockIdAndOrderStatus(stockId, "Placed");

			customerOrdersList.stream().filter(p -> p.getOrderType().equalsIgnoreCase("Limit") && p.getPrice() < price)
					.forEach(p -> {
						p.setOrderStatus("Rejected");
						customerOrdersRepository.save(p);
					});
			customerOrdersList.stream()
					.filter(p -> (p.getOrderType().equalsIgnoreCase("Limit") && p.getPrice() >= price)
							|| (p.getOrderType().equalsIgnoreCase("Market")))
					.forEach(p -> {
						p.setOrderStatus("Accepted");
						customerOrdersRepository.save(p);
					});

			List<CustomerOrderDetails> AcceptedOrdersList = customerOrdersList.stream()
					.filter(p -> (p.getOrderType().equalsIgnoreCase("Limit") && p.getPrice() >= price)
							|| (p.getOrderType().equalsIgnoreCase("Market")))
					.collect(Collectors.toList());

			double sumQuantity = AcceptedOrdersList.stream()
					.collect(Collectors.summingDouble(CustomerOrderDetails::getQuantity));

			logger.info("sumQuantity:" + sumQuantity);

			for (CustomerOrderDetails customerOrderDetails : AcceptedOrdersList) {
				logger.info("customer quantity:" + customerOrderDetails.getQuantity());
				logger.info("admin quantity:" + quantity);
				logger.info("check:" + customerOrderDetails.getQuantity() / sumQuantity);
				Double qunt = (double) ((customerOrderDetails.getQuantity() / sumQuantity) * quantity);
				logger.info("qunt:" + qunt);
				CustomerAcceptedOrders customerAcceptedOrders = new CustomerAcceptedOrders();
				customerAcceptedOrders.setExecutedQuantity(qunt.intValue());
				customerAcceptedOrders.setCreatedDate(LocalDate.now());
				customerAcceptedOrders.setOrderId(customerOrderDetails.getOrderId());
				customerAcceptedOrders.setUserId(customerOrderDetails.getUserId());
				customerAcceptedOrdersRepository.save(customerAcceptedOrders);

				// updating executed_quantity customer_order_details
				customerOrderDetails.setExecutedQuantity(qunt.intValue());
				customerOrdersRepository.save(customerOrderDetails);

			}

		} catch (Exception e) {
			throw new CommonException("Please try after sometime", LocalDateTime.now());
		}

		return "Processed Successfully";

	}

	@Override
	public String changeStatus(String adminId, String status) {
		logger.info(":::::Change market Status Service::::");
		UserMaster userDetails = userMasterRepository.findByUserIdAndUserType(adminId, "A");
		if (Objects.nonNull(userDetails)) {
			MarketStatus marketStatus = marketStatusRepository.findById(adminId).get();
			marketStatus.setMarketStatus(status);
			marketStatusRepository.save(marketStatus);
			return "Market Status Changed Successfully";
		}
		return "Invalid Admin";

	}

	@Override
	public OrderDetailsByStock fetchOrdersByStock(Integer stockId, String fromDate, String toDate) {
		logger.info(":::::Fetchinig order details by stock sService::::");
		logger.info("stockId:" + stockId + " fromDate:" + fromDate + " toDate:" + toDate);

		List<CustomerOrderDetails> customerOrdersList = customerOrdersRepository.findByStockId(stockId);
		if (customerOrdersList.isEmpty()) {
			throw new OrdersNotFoundException("No orders found for the stock", LocalDateTime.now());
		}
		OrderDetailsByStock orderDetailsByStock = new OrderDetailsByStock();
		List<CustomerOrdersDto> custOrdersList = new ArrayList<>();
		try {
			for (CustomerOrderDetails customerOrders : customerOrdersList) {
				CustomerOrdersDto customerOrdersDto = new CustomerOrdersDto();
				orderDetailsByStock.setStockId(stockId);
				orderDetailsByStock.setStockName(customerOrders.getStockName());
				BeanUtils.copyProperties(customerOrders, customerOrdersDto);
				customerOrdersDto.setCustomerId(customerOrders.getUserId());
				customerOrdersDto.setCustomerName(customerOrders.getUserName());
				customerOrdersDto.setOrderQuantity(customerOrders.getQuantity());
				customerOrdersDto.setOrderDate(customerOrders.getOrderDate());
				customerOrdersDto.setOrderStatus(customerOrders.getOrderStatus());
				custOrdersList.add(customerOrdersDto);
			}
			orderDetailsByStock.setCustomerOrdersList(custOrdersList);
		} catch (OrdersNotFoundException e) {
			logger.error(e.getMessage());
			throw new OrdersNotFoundException("No orders found for the stock", LocalDateTime.now());
		}
		return orderDetailsByStock;
	}

	@Override
	public String executeOrders(Integer quantity, Double price, Integer stockId, String orderDate) {
		MarketStatus marketStatus = marketStatusRepository.findByUserIdAndMarketStatus("admin", "Close");
		if (Objects.isNull(marketStatus)) {
			return "Market opened, Please Close the market for processing orders";
		}

		try {
			List<CustomerOrderDetails> customerOrdersList = customerOrdersRepository
					.findByStockIdAndOrderStatus(stockId, "Accepted");

			customerOrdersList.stream()
					.filter(p -> (p.getOrderType().equalsIgnoreCase("Limit") && p.getPrice() >= price)
							|| (p.getOrderType().equalsIgnoreCase("Market")))
					.forEach(p -> {
						p.setOrderStatus("Executed");
						customerOrdersRepository.save(p);
					});

		} catch (Exception e) {
			e.printStackTrace();
			throw new CommonException("Please try after sometime", LocalDateTime.now());

		}
		return "Executed Successfully";

	}

}
