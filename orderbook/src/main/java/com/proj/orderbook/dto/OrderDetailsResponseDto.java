package com.proj.orderbook.dto;

import java.util.List;

public class OrderDetailsResponseDto {

	private Integer customerId;
	private String customerName;
	private List<StockDetailsDto> stockDetails;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
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
