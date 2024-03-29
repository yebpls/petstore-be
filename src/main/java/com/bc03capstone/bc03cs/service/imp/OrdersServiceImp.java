package com.bc03capstone.bc03cs.service.imp;

import com.bc03capstone.bc03cs.DTO.OrdersDTO;

import java.util.List;

public interface OrdersServiceImp {
    List<OrdersDTO> findAllByUserAndIsCompleted(Integer userId, Boolean isCompleted);
    OrdersDTO findById(Integer id);
    Integer add(String jsonString);
    Integer update(String jsonString);
    void hide(Integer id);
    void show(Integer id);
    void delete(Integer id);
}
