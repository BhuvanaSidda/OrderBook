package com.proj.orderbook.dto;

import java.util.List;

public class OrderDetailsResponseDto {

	private String customerId;
	private String customerName;
	private List<StockDetailsDto> stockDetails;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<StockDetailsDto> getStockDetails() {
		return stockDetails;
	}

	public void setStockDetails(List<StockDetailsDto> stockDetails) {
		this.stockDetails = stockDetails;
	}

}
