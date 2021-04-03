package com.proj.orderbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proj.orderbook.entity.CustomerOrderDetails;

@Repository
public interface CustomerOrderDetailsRepository extends JpaRepository<CustomerOrderDetails, Integer>{

}
