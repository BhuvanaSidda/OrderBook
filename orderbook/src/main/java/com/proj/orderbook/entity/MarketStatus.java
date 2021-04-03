package com.proj.orderbook.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MarketStatus {

	@Id
	private String userId;
	
	private String userName;
	
	private String marketStatus;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMarketStatus() {
		return marketStatus;
	}

	public void setMarketStatus(String marketStatus) {
		this.marketStatus = marketStatus;
	}
	
	
}
