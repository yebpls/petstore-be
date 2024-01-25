package com.bc03capstone.bc03cs.controller;

import com.bc03capstone.bc03cs.DTO.CartItemDTO;
import com.bc03capstone.bc03cs.service.imp.CartItemServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/cartItem")
public class CartItemController {

    @Autowired
    private CartItemServiceImp cartItemServiceImp;

    @GetMapping("/findAllByCart")
    public ResponseEntity<?> findAllByCart(@RequestParam Integer cartId) {
        return new ResponseEntity<>(cartItemServiceImp.findAllByCart(cartId), HttpStatus.OK);
    }

    @GetMapping("/findById")
    public ResponseEntity<?> findById(@RequestParam Integer id) {
        return new ResponseEntity<>(cartItemServiceImp.findById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody CartItemDTO cartItemDTO) {
        cartItemServiceImp.add(cartItemDTO);
        return new ResponseEntity<>("Add cartItem success", HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody CartItemDTO cartItemDTO) {
        cartItemServiceImp.update(cartItemDTO);
        return new ResponseEntity<>("update cartItem success", HttpStatus.OK);
    }

    @PostMapping("/hide/{id}")
    public ResponseEntity<?> hide(@PathVariable Integer id) {
        cartItemServiceImp.hide(id);
        return new ResponseEntity<>("hide cartItem success", HttpStatus.OK);
    }

    @PostMapping("/show/{id}")
    public ResponseEntity<?> show(@PathVariable Integer id) {
        cartItemServiceImp.show(id);
        return new ResponseEntity<>("show cartItem success", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        cartItemServiceImp.delete(id);
        return new ResponseEntity<>("delete cartItem success", HttpStatus.OK);
    }
}
