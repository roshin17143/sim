package com.project.group16.product.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.group16.product.dto.SubscribedProdDTO;
import com.project.group16.product.entity.SubscribedProd;
import com.project.group16.product.exception.Group16ProjectException;
import com.project.group16.product.repository.SubscribedProdRepository;

@Service
public class SubscribedProdService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	SubscribedProdRepository subscribedprodrepo;
	
	//Get subscribed product of buyer
	public List<SubscribedProdDTO> getByBuyerid(@PathVariable String buyerid) throws Group16ProjectException{
		logger.info("Products for the buyer with id {}", buyerid);
		Iterable<SubscribedProd> sub = subscribedprodrepo.findByBuyerid(buyerid);
		List<SubscribedProdDTO> subscribedprodDTO = new ArrayList<SubscribedProdDTO>();
		sub.forEach(subs -> {
			subscribedprodDTO.add(SubscribedProdDTO.valueOf(subs));
		});
		if(subscribedprodDTO.isEmpty())
			throw new Group16ProjectException("Service.EMPTY_BUYER_PRODUCTS");
		return subscribedprodDTO;
	}
	
	//Get all subscribed products
	public List<SubscribedProdDTO> getAllSubProduct() throws Group16ProjectException{
		Iterable<SubscribedProd> subs = subscribedprodrepo.findAll();
		List<SubscribedProdDTO> subscribedprodDTOs = new ArrayList<>();
		subs.forEach(subpro -> {
			SubscribedProdDTO subscribedprodDTO = SubscribedProdDTO.valueOf(subpro);
			subscribedprodDTOs.add(subscribedprodDTO);
		});
		if(subscribedprodDTOs.isEmpty())
			throw new Group16ProjectException("Service.NO_SUBSCRIBED_PRODUCTS");
		logger.info("Subscribed Product Details : {}", subscribedprodDTOs);
		return subscribedprodDTOs;
	}
	
	public void createSubscribedProduct(SubscribedProdDTO subscribeProdDTO) throws Group16ProjectException {
		logger.info("Creation request for customer {} with data {}", subscribeProdDTO);
		SubscribedProd subscribe = null;
//		if(subscribeProdDTO.getBuyerid() != null) {
//			subscribe = subscribeProdDTO.createEntity();	
//		}
//		System.out.println("wishlist" + subscribe);
		subscribe = subscribeProdDTO.createEntity();	
		if (subscribe != null) {
			subscribedprodrepo.save(subscribe);
		} else {
			throw new Group16ProjectException("Service.NO_WISHLIST");
		}

	}
	
}
