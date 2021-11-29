package com.project.group16.orderDetails.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.group16.orderDetails.entity.Order;

public interface ReorderRepository extends JpaRepository<Order, String>{
	Order findByOrderid(String orderid);
}
