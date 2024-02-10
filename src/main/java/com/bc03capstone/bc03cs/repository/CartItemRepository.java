package com.bc03capstone.bc03cs.repository;

import com.bc03capstone.bc03cs.entity.Cart;
import com.bc03capstone.bc03cs.entity.CartItem;
import com.bc03capstone.bc03cs.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer>, JpaSpecificationExecutor<CartItem> {
    List<CartItem> findAllByCartAndStatus(Cart cart, Boolean status);
    CartItem findByIdAndStatus(Integer id, Boolean status);
    CartItem findByPet(Pet pet);
}