package com.proj.orderbook.dto;

import java.util.List;

public class OrderDetailsByStock {

	private Integer stockId;
	private String stockName;
	private List<CustomerOrdersDto> customerOrdersList;

	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public List<CustomerOrdersDto> getCustomerOrdersList() {
		return customerOrdersList;
	}

	public void setCustomerOrdersList(List<CustomerOrdersDto> customerOrdersList) {
		this.customerOrdersList = customerOrdersList;
	}

}
