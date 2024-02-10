package com.bc03capstone.bc03cs.controller;

import com.bc03capstone.bc03cs.service.imp.CartServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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

    @PostMapping("/hide/{id}")
    public ResponseEntity<?> hide(@PathVariable Integer id) {
        cartServiceImp.hide(id);
        logger.info("hide cart id: " + id);
        return new ResponseEntity<>("hide cart success", HttpStatus.OK);
    }

    @PostMapping("/show/{id}")
    public ResponseEntity<?> show(@PathVariable Integer id) {
        cartServiceImp.show(id);
        logger.info("show cart id: " + id);
        return new ResponseEntity<>("show cart success", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        cartServiceImp.delete(id);
        logger.info("delete cart id: " + id);
        return new ResponseEntity<>("delete cart success", HttpStatus.OK);
    }
}
