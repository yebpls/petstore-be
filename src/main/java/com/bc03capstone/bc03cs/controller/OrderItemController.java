package com.bc03capstone.bc03cs.controller;

import com.bc03capstone.bc03cs.DTO.OrderItemDTO;
import com.bc03capstone.bc03cs.service.imp.OrderItemServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/orderItem")
public class OrderItemController {

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
    public ResponseEntity<?> add(@RequestBody OrderItemDTO orderItemDTO) {
        orderItemServiceImp.add(orderItemDTO);
        return new ResponseEntity<>("Add orderItem success", HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody OrderItemDTO orderItemDTO) {
        orderItemServiceImp.update(orderItemDTO);
        return new ResponseEntity<>("update orderItem success", HttpStatus.OK);
    }

    @PostMapping("/hide/{id}")
    public ResponseEntity<?> hide(@PathVariable Integer id) {
        orderItemServiceImp.hide(id);
        return new ResponseEntity<>("hide orderItem success", HttpStatus.OK);
    }

    @PostMapping("/show/{id}")
    public ResponseEntity<?> show(@PathVariable Integer id) {
        orderItemServiceImp.show(id);
        return new ResponseEntity<>("show orderItem success", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        orderItemServiceImp.delete(id);
        return new ResponseEntity<>("delete orderItem success", HttpStatus.OK);
    }
}
