package com.bc03capstone.bc03cs.controller;

import com.bc03capstone.bc03cs.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Validated
@RestController
@RequestMapping("/api/orderItem")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;
}
