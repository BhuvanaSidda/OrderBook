package com.proj.orderbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proj.orderbook.entity.StockDetails;

@Repository
public interface StockDetailsRepository extends JpaRepository<StockDetails, Integer> {

}
