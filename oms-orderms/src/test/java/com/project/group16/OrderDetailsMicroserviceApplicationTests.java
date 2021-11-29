package com.project.group16;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.group16.orderDetails.dto.OrderDTO;
import com.project.group16.orderDetails.entity.Order;
import com.project.group16.orderDetails.exception.Group16ProjectException;
import com.project.group16.orderDetails.repository.OrderRepository;
import com.project.group16.orderDetails.service.OrderService;
import com.project.group16.orderDetails.service.ProductOrderService;
import com.project.group16.orderDetails.repository.ProductsOrderRepository;



@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailsMicroserviceApplicationTests {

//	@Test
//	public void contextLoads() {
//	}

	@Mock
	OrderRepository orderRepository;

	@InjectMocks
	OrderService orderService = new OrderService();

	@Mock
	ProductsOrderRepository productsOrderedRepo;

	@InjectMocks
	ProductOrderService productOrderService = new ProductOrderService();

	@Test
	public void orderValidTest() throws Group16ProjectException {
		List<OrderDTO> orderList = new ArrayList<OrderDTO>();

		OrderDTO orderEntity = new OrderDTO();
		orderEntity.setOrderid("1");
		orderEntity.setBuyerid("B101");
		orderEntity.setAmount(1000.0);
		orderEntity.setAddress("KUPT");
		orderEntity.setOrderdate(new Date(2020-9-12));
		orderEntity.setStatus("ORDERPLACED");

//		orderList.add(orderEntity);

//		Mockito.when(orderRepository.findAll()).thenReturn(orderEntity);

		List<OrderDTO> reProduct = orderService.getAllOrder();

		Assertions.assertEquals(reProduct.isEmpty(), orderList.isEmpty());

	}

	@Test
	public void orderInvalidTest() throws Group16ProjectException {
		List<OrderDTO> orderList = new ArrayList<OrderDTO>();

		OrderDTO orderEntity = new OrderDTO();
		
		orderEntity.setOrderid("21");
		orderEntity.setBuyerid("B11");
		orderEntity.setAmount(1000.0);
		orderEntity.setAddress("KUPT");
		orderEntity.setOrderdate(new Date(220-9-12));
		orderEntity.setStatus("ORDERPLACED");

		OrderDTO fromRepository = new OrderDTO();
		fromRepository.setOrderid("1");
		fromRepository.setBuyerid("B101");
		fromRepository.setAmount(1000.0);
		fromRepository.setAddress("KUPT");
		fromRepository.setOrderdate(new Date(2020-9-12));
		fromRepository.setStatus("ORDERPLACED");
		
		Mockito.when(orderRepository.findAll());
//	    InfyMarketException exception=Assertions.assertThrows(InfyMarketException.class,()->orderService.getAllOrder());
//		Assertions.assertEquals("Service.WRONG_CREDENTIALS", exception.getMessage());
		
//		Optional opt = Optional.of(orderEntity);// Valid
//
//		Optional opt1 = Optional.empty();// Invalid
//		
//
//		Mockito.when(orderRepository.findById(Mockito.anyString())).thenReturn(opt1);
//
////	     Mockito.when(productrepo.findAll()).thenReturn(productList);
//
//		List<OrderDTO> reProduct = orderService.getAllOrder();
////	          System.out.println(reProduct.get(0));
//		Assertions.assertEquals(reProduct.isEmpty(), orderList.isEmpty());
	}
}
