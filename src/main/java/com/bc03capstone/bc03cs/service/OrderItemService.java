package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.repository.OrderItemRepository;
import com.bc03capstone.bc03cs.service.imp.OrderItemServiceImp;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class OrderItemService implements OrderItemServiceImp {
    @Autowired
    private OrderItemRepository orderItemRepository;
}
