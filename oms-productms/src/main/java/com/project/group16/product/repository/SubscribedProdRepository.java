package com.project.group16.product.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.group16.product.entity.SubscribedProd;

	public interface SubscribedProdRepository extends JpaRepository<SubscribedProd, String>{
		List<SubscribedProd> findByBuyerid(String buyerid);
	}

