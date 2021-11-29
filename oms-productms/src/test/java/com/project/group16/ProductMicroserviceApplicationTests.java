package com.project.group16;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.group16.product.dto.ProductDTO;
import com.project.group16.product.dto.SubscribedProdDTO;
import com.project.group16.product.entity.Product;
import com.project.group16.product.entity.SubscribedProd;
import com.project.group16.product.exception.Group16ProjectException;
import com.project.group16.product.repository.ProductRepository;
import com.project.group16.product.repository.SubscribedProdRepository;
import com.project.group16.product.service.ProductService;
import com.project.group16.product.service.SubscribedProdService;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductMicroserviceApplicationTests {

//	@Test
//	public void contextLoads() {
//	}

	@Mock
	ProductRepository productrepo;

	@InjectMocks
	ProductService productService = new ProductService();

	@Mock
	SubscribedProdRepository subscribedprodrepo;

	@InjectMocks
	SubscribedProdService subscribedprodService = new SubscribedProdService();

	@Test
	public void productValidTest() throws Group16ProjectException {
		List<Product> productList = new ArrayList<Product>();

		Product productEntity = new Product();
		productEntity.setProdid("P101");
		productEntity.setProductname("electronics");
		productEntity.setPrice(500.0);
		productEntity.setStock(10000);
		productEntity.setDescription("mobile");
		productEntity.setImage("https://m.media-amazon.com/images/I/41jRzGyDUJL.jpeg");
		productEntity.setSellerid("1");
		productEntity.setCategory("smart phone");
		productEntity.setSubcategory("one plus");
		productEntity.setProductrating(4);

		productList.add(productEntity);

		Mockito.when(productrepo.findAll()).thenReturn(productList);

		List<ProductDTO> reProduct = productService.getAllProduct();

		Assertions.assertEquals(reProduct.isEmpty(), productList.isEmpty());

	}

	@Test
	public void productinvalidtest() throws Group16ProjectException {
		List<Product> productList = new ArrayList<Product>();

		Product productEntity = new Product();
		productEntity.setProdid("P101");
		productEntity.setProductname("vivo");
		productEntity.setPrice(5000.0);
		productEntity.setStock(5);
		productEntity.setDescription("abc");
		productEntity.setImage("asada");
		productEntity.setSellerid("2");
		productEntity.setCategory("ads");
		productEntity.setSubcategory("ads");
		productEntity.setProductrating(3);

		Optional opt = Optional.of(productEntity);// Valid

		Optional opt1 = Optional.empty();// Invalid

		Mockito.when(productrepo.findById(Mockito.anyString())).thenReturn(opt1);

//	     Mockito.when(productrepo.findAll()).thenReturn(productList);

		List<ProductDTO> reProduct = productService.getAllProduct();
//	          System.out.println(reProduct.get(0));
		Assertions.assertEquals(reProduct.isEmpty(), productList.isEmpty());
	}

	@Test
	public void subscribedprodValidTest() throws Group16ProjectException {
		List<SubscribedProd> subscribedprodList = new ArrayList<SubscribedProd>();

		SubscribedProd subscribedprodEntity = new SubscribedProd();
		subscribedprodEntity.setProdid("P101");
		subscribedprodEntity.setBuyerid("electronics");
		subscribedprodEntity.setQuantity(50);

		Optional product1 = Optional.of(subscribedprodEntity);

		subscribedprodList.add(subscribedprodEntity);

		Mockito.when(subscribedprodrepo.findAll()).thenReturn(subscribedprodList);

		List<SubscribedProdDTO> reProduct = subscribedprodService.getAllSubProduct();

		Assertions.assertEquals(reProduct.isEmpty(), subscribedprodList.isEmpty());

	}

	@Test
	public void subscribedprodinValidTest() throws Group16ProjectException {
		List<SubscribedProd> subscribedprodList = new ArrayList<SubscribedProd>();

		SubscribedProd subscribedprodEntity = new SubscribedProd();
		subscribedprodEntity.setProdid("P101");
		subscribedprodEntity.setBuyerid("electronics");
		subscribedprodEntity.setQuantity(50);

//	     Optional opt1=Optional.empty();  
		Optional opt = Optional.of(subscribedprodEntity);// Valid

		Optional opt1 = Optional.empty();// Invalid

		Mockito.when(subscribedprodrepo.findById(Mockito.anyString())).thenReturn(opt1);

//	Mockito.when(subscribedprodrepo.findAll()).thenReturn(opt1);

		List<SubscribedProdDTO> reProduct = subscribedprodService.getAllSubProduct();
		Assertions.assertEquals(reProduct.isEmpty(), subscribedprodList.isEmpty());

	}

}
