package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;
}