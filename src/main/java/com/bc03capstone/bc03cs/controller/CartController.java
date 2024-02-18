package com.bc03capstone.bc03cs.controller;

import com.bc03capstone.bc03cs.service.imp.CartServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartServiceImp cartServiceImp;

    @GetMapping("/findByUser")
    public ResponseEntity<?> findByUser(@RequestParam Integer userId) {
        return new ResponseEntity<>(cartServiceImp.findByUser(userId), HttpStatus.OK);
    }

    @GetMapping("/findById")
    public ResponseEntity<?> findById(@RequestParam Integer id) {
        return new ResponseEntity<>(cartServiceImp.findById(id), HttpStatus.OK);
    }
}
