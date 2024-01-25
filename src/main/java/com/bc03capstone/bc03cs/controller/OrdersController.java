package com.bc03capstone.bc03cs.controller;

import com.bc03capstone.bc03cs.DTO.OrdersDTO;
import com.bc03capstone.bc03cs.DTO.SpeciesDTO;
import com.bc03capstone.bc03cs.service.OrdersService;
import com.bc03capstone.bc03cs.service.imp.OrdersServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/orders")
public class OrdersController {

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
    public ResponseEntity<?> add(@RequestBody OrdersDTO ordersDTO) {
        ordersServiceImp.add(ordersDTO);
        return new ResponseEntity<>("Add orders success", HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody OrdersDTO ordersDTO) {
        ordersServiceImp.update(ordersDTO);
        return new ResponseEntity<>("update orders success", HttpStatus.OK);
    }

    @PostMapping("/hide/{id}")
    public ResponseEntity<?> hide(@PathVariable Integer id) {
        ordersServiceImp.hide(id);
        return new ResponseEntity<>("hide orders success", HttpStatus.OK);
    }

    @PostMapping("/show/{id}")
    public ResponseEntity<?> show(@PathVariable Integer id) {
        ordersServiceImp.show(id);
        return new ResponseEntity<>("show orders success", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        ordersServiceImp.delete(id);
        return new ResponseEntity<>("delete orders success", HttpStatus.OK);
    }
}
