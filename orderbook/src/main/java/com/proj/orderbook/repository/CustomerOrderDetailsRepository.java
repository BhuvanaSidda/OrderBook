package com.proj.orderbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proj.orderbook.entity.CustomerOrderDetails;

@Repository
public interface CustomerOrderDetailsRepository extends JpaRepository<CustomerOrderDetails, Integer>{

	List<CustomerOrderDetails> findByStockId(Integer stockId);

	List<CustomerOrderDetails> findByStockIdAndOrderStatus(Integer stockId, String string);

	List<CustomerOrderDetails> findByUserId(String customerId);

}
