package com.bc03capstone.bc03cs.controller;

import com.bc03capstone.bc03cs.DTO.CartItemDTO;
import com.bc03capstone.bc03cs.service.imp.CartItemServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/cartItem")
public class CartItemController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
        Integer id = cartItemServiceImp.add(cartItemDTO);
        logger.info("add cartItem id: " + id);
        return new ResponseEntity<>("Add cartItem success", HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody CartItemDTO cartItemDTO) {
        cartItemServiceImp.update(cartItemDTO);
        logger.info("update cartItem id: " + cartItemDTO.getId());
        return new ResponseEntity<>("update cartItem success", HttpStatus.OK);
    }

    @PostMapping("/hide/{id}")
    public ResponseEntity<?> hide(@PathVariable Integer id) {
        cartItemServiceImp.hide(id);
        logger.info("hide cartItem id: " + id);
        return new ResponseEntity<>("hide cartItem success", HttpStatus.OK);
    }

    @PostMapping("/show/{id}")
    public ResponseEntity<?> show(@PathVariable Integer id) {
        cartItemServiceImp.show(id);
        logger.info("show cartItem id: " + id);
        return new ResponseEntity<>("show cartItem success", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        cartItemServiceImp.delete(id);
        logger.info("delete cartItem id: " + id);
        return new ResponseEntity<>("delete cartItem success", HttpStatus.OK);
    }
}
