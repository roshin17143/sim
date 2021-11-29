package com.project.group16.orderDetails.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.group16.orderDetails.dto.ProductsorderedDTO;
import com.project.group16.orderDetails.entity.Productsorder;
import com.project.group16.orderDetails.exception.Group16ProjectException;
import com.project.group16.orderDetails.repository.ProductsOrderRepository;

@Service
@Transactional
public class ProductOrderService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductsOrderRepository orderproRepo;

	public List<ProductsorderedDTO> getProductById(String prodid) throws Group16ProjectException{
		logger.info("Productname request for product {}", prodid);
		Iterable<Productsorder> proord = orderproRepo.findByProdid(prodid);
		List<ProductsorderedDTO> proorderDTO = new ArrayList<ProductsorderedDTO>();
		proord.forEach(pord -> {
			proorderDTO.add(ProductsorderedDTO.valueOf(pord));
		});
		logger.info("Productname for product : {}", proord);
		return proorderDTO;
	}
}
