package com.bc03capstone.bc03cs.controller;

import com.bc03capstone.bc03cs.service.imp.OrderItemServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/orderItem")
public class OrderItemController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderItemServiceImp orderItemServiceImp;

    @GetMapping("/findAllByOrders")
    public ResponseEntity<?> findAllByOrders(@RequestParam Integer orderId) {
        return new ResponseEntity<>(orderItemServiceImp.findAllByOrders(orderId), HttpStatus.OK);
    }

    @GetMapping("/findById")
    public ResponseEntity<?> findById(@RequestParam Integer id) {
        return new ResponseEntity<>(orderItemServiceImp.findById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody String jsonString) {
        Integer id = orderItemServiceImp.add(jsonString);
        logger.info("Add orderItem id: " + id);
        return new ResponseEntity<>("Add orderItem success", HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody String jsonString) {
        Integer id = orderItemServiceImp.update(jsonString);
        logger.info("update orderItem id: " + id);
        return new ResponseEntity<>("update orderItem success", HttpStatus.OK);
    }

    @PostMapping("/hide/{id}")
    public ResponseEntity<?> hide(@PathVariable Integer id) {
        orderItemServiceImp.hide(id);
        logger.info("hide orderItem id: " + id);
        return new ResponseEntity<>("hide orderItem success", HttpStatus.OK);
    }

    @PostMapping("/show/{id}")
    public ResponseEntity<?> show(@PathVariable Integer id) {
        orderItemServiceImp.show(id);
        logger.info("show orderItem id: " + id);
        return new ResponseEntity<>("show orderItem success", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        orderItemServiceImp.delete(id);
        logger.info("delete orderItem id: " + id);
        return new ResponseEntity<>("delete orderItem success", HttpStatus.OK);
    }
}
