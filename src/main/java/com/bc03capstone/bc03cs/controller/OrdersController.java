package com.bc03capstone.bc03cs.controller;

import com.bc03capstone.bc03cs.service.imp.OrdersServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/orders")
public class OrdersController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrdersServiceImp ordersServiceImp;

    @GetMapping("/findAllByUserAndIsCompleted")
    public ResponseEntity<?> findAllByUserAndIsCompleted(@RequestParam Integer userId, @RequestParam Boolean isCompleted) {
        return new ResponseEntity<>(ordersServiceImp.findAllByUserAndIsCompleted(userId,isCompleted), HttpStatus.OK);
    }

    @GetMapping("/findById")
    public ResponseEntity<?> findById(@RequestParam Integer id) {
        return new ResponseEntity<>(ordersServiceImp.findById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody String jsonString) {
        Integer id = ordersServiceImp.add(jsonString);
        logger.info("Add orders id: " + id);
        return new ResponseEntity<>("Add orders success", HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody String jsonString) {
        Integer id = ordersServiceImp.update(jsonString);
        logger.info("update orders id: " + id);
        return new ResponseEntity<>("update orders success", HttpStatus.OK);
    }

    @PostMapping("/hide/{id}")
    public ResponseEntity<?> hide(@PathVariable Integer id) {
        ordersServiceImp.hide(id);
        logger.info("hide orders id: " + id);
        return new ResponseEntity<>("hide orders success", HttpStatus.OK);
    }

    @PostMapping("/show/{id}")
    public ResponseEntity<?> show(@PathVariable Integer id) {
        ordersServiceImp.show(id);
        logger.info("show orders id: " + id);
        return new ResponseEntity<>("show orders success", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        ordersServiceImp.delete(id);
        logger.info("delete orders id: " + id);
        return new ResponseEntity<>("delete orders success", HttpStatus.OK);
    }
}
