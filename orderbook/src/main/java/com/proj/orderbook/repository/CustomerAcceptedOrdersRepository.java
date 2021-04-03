package com.proj.orderbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proj.orderbook.entity.CustomerAcceptedOrders;

@Repository
public interface CustomerAcceptedOrdersRepository extends JpaRepository<CustomerAcceptedOrders, Integer>{

}
