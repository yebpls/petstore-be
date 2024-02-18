package com.bc03capstone.bc03cs.service.imp;

import com.bc03capstone.bc03cs.DTO.OrderItemDTO;

import java.util.List;

public interface OrderItemServiceImp {
    List<OrderItemDTO> findAllByOrders(Integer orderId);
    OrderItemDTO findById(Integer id);
    Integer add(String jsonString);
    Integer update(String jsonString);
    void hide(Integer id);
    void show(Integer id);
    void delete(Integer id);
}
