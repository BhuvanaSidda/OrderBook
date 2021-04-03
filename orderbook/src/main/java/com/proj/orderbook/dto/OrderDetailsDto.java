package com.proj.orderbook.dto;

import java.time.LocalDateTime;

public class OrderDetailsDto {

	private String customerId;
	private Integer stockId;
	private Integer orderQuantity;
	private String orderType;
	private Double price;
	private LocalDateTime orderDate;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	
	public Integer getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

}
