package com.bc03capstone.bc03cs.service.imp;

import com.bc03capstone.bc03cs.DTO.CartItemDTO;

import java.util.List;

public interface CartItemServiceImp {
    List<CartItemDTO> findAllByCart(Integer cartId);
    CartItemDTO findById(Integer id);
    void add(CartItemDTO cartItemDTO);
    void update(CartItemDTO cartItemDTO);
    void hide(Integer id);
    void show(Integer id);
    void delete(Integer id);
}
