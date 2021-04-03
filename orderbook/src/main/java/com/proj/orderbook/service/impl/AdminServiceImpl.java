package com.proj.orderbook.service.impl;

import java.time.LocalDate;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.orderbook.entity.MarketStatus;
import com.proj.orderbook.entity.UserMaster;
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

	@Override
	public String processOrder(Integer quantity, Double price, Integer stockId, LocalDate orderDate) {
		// TODO Auto-generated method stub
		return null;
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

}
