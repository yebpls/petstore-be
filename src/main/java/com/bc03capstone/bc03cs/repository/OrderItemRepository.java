package com.bc03capstone.bc03cs.repository;

import com.bc03capstone.bc03cs.entity.OrderItem;
import com.bc03capstone.bc03cs.entity.Orders;
import com.bc03capstone.bc03cs.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>, JpaSpecificationExecutor<OrderItem> {
    List<OrderItem> findAllByOrdersAndStatus(Orders orders, Boolean status);
    OrderItem findByIdAndStatus(Integer id, Boolean status);
    OrderItem findByPet(Pet pet);
}