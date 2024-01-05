package com.bc03capstone.bc03cs.repository;

import com.bc03capstone.bc03cs.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrdersRepository extends JpaRepository<Orders, Integer>, JpaSpecificationExecutor<Orders> {

}