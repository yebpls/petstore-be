package com.bc03capstone.bc03cs.repository;

import com.bc03capstone.bc03cs.entity.Cart;
import com.bc03capstone.bc03cs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>, JpaSpecificationExecutor<Cart> {
    Cart findByUserAndStatus(User user, Boolean status);
    Cart findByIdAndStatus(Integer id, Boolean status);
}