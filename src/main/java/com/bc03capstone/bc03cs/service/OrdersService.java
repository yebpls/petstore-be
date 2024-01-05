package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.repository.OrdersRepository;
import com.bc03capstone.bc03cs.service.imp.OrdersServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersService implements OrdersServiceImp {

    @Autowired
    private OrdersRepository ordersRepository;
}
