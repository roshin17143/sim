package com.project.group16.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.group16.user.dto.BuyerDTO;
import com.project.group16.user.dto.CartDTO;
import com.project.group16.user.dto.LoginDTO;
import com.project.group16.user.dto.WishlistDTO;
import com.project.group16.user.entity.Buyer;
import com.project.group16.user.entity.Cart;
import com.project.group16.user.entity.Wishlist;
import com.project.group16.user.exception.Group16ProjectException;
import com.project.group16.user.repository.BuyerRepository;
import com.project.group16.user.repository.CartRepository;
import com.project.group16.user.repository.WishlistRepository;

@Service
@Transactional
public class BuyerService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	BuyerRepository buyerrepo;

	@Autowired
	WishlistRepository wishrepo;

	@Autowired
	CartRepository cartrepo;

	//Register buyer
	public void saveBuyer(BuyerDTO buyerDTO) throws Group16ProjectException {
		logger.info("Registration request for buyer with data {}", buyerDTO);
		Buyer buyer = buyerDTO.createBuyer();
		buyerrepo.save(buyer);
	}

	//Get all buyer details
	public List<BuyerDTO> getAllBuyer() throws Group16ProjectException {

		Iterable<Buyer> buyers = buyerrepo.findAll();
		List<BuyerDTO> buyerDTOs = new ArrayList<>();

		buyers.forEach(buyer -> {
			BuyerDTO buyerDTO = BuyerDTO.valueOf(buyer);
			buyerDTOs.add(buyerDTO);
		});
		if (buyerDTOs.isEmpty())
			throw new Group16ProjectException("Service.BUYERS_NOT_FOUND");
		logger.info("Buyer Details : {}", buyerDTOs);
		return buyerDTOs;
	}

	//Get buyer by id
	public BuyerDTO getBuyerById(String buyerId) throws Group16ProjectException {
		BuyerDTO buyerDTO = null;
		logger.info("Profile request for buyer {}", buyerId);
		Optional<Buyer> optBuyer = buyerrepo.findById(buyerId);
		if (optBuyer.isPresent()) {
			Buyer buyer = optBuyer.get();
			buyerDTO = BuyerDTO.valueOf(buyer);
		} else {
			throw new Group16ProjectException("Service.BUYERS_NOT_FOUND");
		}
		logger.info("Profile for buyer : {}", buyerDTO);
		return buyerDTO;
	}

	//Buyer Login 
	public boolean login(LoginDTO loginDTO) throws Group16ProjectException {
		logger.info("Login request for buyer {} with password {}", loginDTO.getEmail(), loginDTO.getPassword());
		Buyer buy = buyerrepo.findByEmail(loginDTO.getEmail());
		if (buy != null && buy.getPassword().equals(loginDTO.getPassword())) {
			return true;
		} else {
			throw new Group16ProjectException("Service.DETAILS_NOT_FOUND");
		}
	}

	//Delete buyer
	public void deleteBuyer(String buyerid) throws Group16ProjectException {
		Optional<Buyer> buyer = buyerrepo.findById(buyerid);
		buyer.orElseThrow(() -> new Group16ProjectException("Service.BUYERS_NOT_FOUND"));
		buyerrepo.deleteById(buyerid);
	}

//	//Get wishlist of buyer
//	public WishlistDTO getWishlistOfBuyer(String buyerid) throws InfyMarketException {
//		Wishlist wishlist = buyerrepo.getWishlist(buyerid);
//		WishlistDTO wishlistDTOs = null;
//		if (wishlist != null) {
//			wishlistDTOs = WishlistDTO.valueOf(wishlist);
//		} else {
//			throw new InfyMarketException("Service.ORDERS_NOT_FOUND");
//		}
//		return wishlistDTOs;
//	}

	//Add product to wishlist
	public void createWishlist(WishlistDTO wishlistDTO) throws Group16ProjectException {
		logger.info("Creation request for customer {} with data {}", wishlistDTO);
		Wishlist wishlist = wishlistDTO.createEntity();
		System.out.println("wishlist" + wishlist);
		if (wishlist != null) {
			wishrepo.save(wishlist);
		} else {
			throw new Group16ProjectException("Service.NO_WISHLIST");
		}

	}

	//Delete product from wishlist
	public void deleteWishlist(String buyerid) throws Group16ProjectException {
		Optional<Wishlist> buyer = wishrepo.findById(buyerid);
		buyer.orElseThrow(() -> new Group16ProjectException("Service.Buyer_NOT_FOUND"));
		wishrepo.deleteById(buyerid);
	}
	
	//Add product to cart
	public void createCart(CartDTO cartDTO) throws Group16ProjectException {
		logger.info("Adding product to cart request for buyer {} with data {}", cartDTO);
		Cart cart = cartDTO.createEntity();
		System.out.println("cart" + cart);
		if (cart != null) {
			cartrepo.save(cart);
		} else {
			throw new Group16ProjectException("Service.NO_CART_DETAILS");
		}

	}

	//Delete product from cart
	public void deleteCart(String buyerid) throws Group16ProjectException {
		Optional<Cart> buyer = cartrepo.findById(buyerid);
		buyer.orElseThrow(() -> new Group16ProjectException("Service.Buyer_NOT_FOUND"));
		cartrepo.deleteById(buyerid);
	}

	//Update isprivileged
	public Buyer updateIsprivilege(Buyer buyer, String buyerid) throws Group16ProjectException{
		Buyer existingBuyer = buyerrepo.findById(buyerid).orElse(null);
		if ((existingBuyer != null) && (existingBuyer.getRewardpoints() >= 10000)) {
			existingBuyer.setIsprivileged(buyer.getIsprivileged());
			return buyerrepo.save(existingBuyer);
		}else {
			throw new Group16ProjectException("Service.NO_REWARD_POINTS");
		}
	}

}
