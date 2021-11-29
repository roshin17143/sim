package com.project.group16.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.group16.user.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, String>{

}
