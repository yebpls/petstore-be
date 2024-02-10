package com.bc03capstone.bc03cs.repository;

import com.bc03capstone.bc03cs.entity.Orders;
import com.bc03capstone.bc03cs.entity.ShipLocation;
import com.bc03capstone.bc03cs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer>, JpaSpecificationExecutor<Orders> {
    Orders findByShipLocation(ShipLocation shipLocation);
    Orders findByIdAndStatus(Integer id, Boolean status);
    List<Orders> findAllByUserAndIsCompletedAndStatus(User user, Boolean isCompleted, Boolean status);
}