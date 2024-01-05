package com.bc03capstone.bc03cs.controller;

import com.bc03capstone.bc03cs.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@CrossOrigin
@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;
}
