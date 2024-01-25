package com.bc03capstone.bc03cs.service.imp;

import com.bc03capstone.bc03cs.DTO.CartDTO;

public interface CartServiceImp {
    CartDTO findByUser(Integer userId);
    CartDTO findById(Integer id);
    void add(CartDTO cartDTO);
    void hide(Integer id);
    void show(Integer id);
    void delete(Integer id);
}
