package com.proj.orderbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proj.orderbook.entity.MarketStatus;

@Repository
public interface MarketStatusRepository extends JpaRepository<MarketStatus, String>{
}
