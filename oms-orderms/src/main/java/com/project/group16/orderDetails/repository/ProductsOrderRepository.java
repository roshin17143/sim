package com.project.group16.orderDetails.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.group16.orderDetails.entity.Productsorder;

public interface  ProductsOrderRepository extends JpaRepository<Productsorder, String>{
	
	List<Productsorder> findByProdid(String prodid);
	}

