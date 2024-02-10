package com.bc03capstone.bc03cs.service.imp;

import com.bc03capstone.bc03cs.DTO.CartDTO;
import com.bc03capstone.bc03cs.entity.User;

public interface CartServiceImp {
    CartDTO findByUser(Integer userId);
    CartDTO findById(Integer id);
    void add(User user);
    void hide(Integer id);
    void show(Integer id);
    void delete(Integer id);
}
