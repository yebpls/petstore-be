package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.repository.CartItemRepository;
import com.bc03capstone.bc03cs.service.imp.CartServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CartItemService implements CartServiceImp {

    @Autowired
    private CartItemRepository cartItemRepository;
}
