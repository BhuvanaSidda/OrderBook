package com.proj.orderbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proj.orderbook.entity.UserMaster;

@Repository
public interface UserMasterRepository extends JpaRepository<UserMaster, String> {

	UserMaster findByUserIdAndUserType(String adminId, String string);

}
