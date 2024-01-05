package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;
}
