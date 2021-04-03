package com.proj.orderbook.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CustomerAcceptedOrders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer acceptedId;

	private Integer orderId;

	private String userId;

	private Integer executedQuantity;

	private LocalDate createdDate;

	public Integer getAcceptedId() {
		return acceptedId;
	}

	public void setAcceptedId(Integer acceptedId) {
		this.acceptedId = acceptedId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getExecutedQuantity() {
		return executedQuantity;
	}

	public void setExecutedQuantity(Integer executedQuantity) {
		this.executedQuantity = executedQuantity;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

}
