package com.proj.orderbook.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StockDetails {

	@Id
	private Integer stockId;
	private String stockName;
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
	
	
	
}
