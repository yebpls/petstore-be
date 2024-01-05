package com.bc03capstone.bc03cs.repository;

import com.bc03capstone.bc03cs.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CartRepository extends JpaRepository<Cart, Integer>, JpaSpecificationExecutor<Cart> {

}