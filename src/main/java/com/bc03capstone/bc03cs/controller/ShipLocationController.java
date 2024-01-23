package com.bc03capstone.bc03cs.controller;

import com.bc03capstone.bc03cs.DTO.ShipLocationDTO;
import com.bc03capstone.bc03cs.service.imp.ShipLocationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/shipLocation")
public class ShipLocationController {

    @Autowired
    private ShipLocationServiceImp shipLocationServiceImp;

    @GetMapping("/findAllByUser")
    public ResponseEntity<?> findAllByUser(@RequestParam Integer userId) {
        return new ResponseEntity<>(shipLocationServiceImp.findAllByUser(userId), HttpStatus.OK);
    }

    @GetMapping("/findByUserAndIsDefault")
    public ResponseEntity<?> findByUserAndIsDefault(@RequestParam Integer userId) {
        return new ResponseEntity<>(shipLocationServiceImp.findByUserAndIsDefault(userId), HttpStatus.OK);
    }

    @GetMapping("/findById")
    public ResponseEntity<?> findById(@RequestParam Integer id) {
        return new ResponseEntity<>(shipLocationServiceImp.findById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody ShipLocationDTO shipLocationDTO) {
        shipLocationServiceImp.add(shipLocationDTO);
        return new ResponseEntity<>("Add shipLocation success", HttpStatus.OK);
    }

    @PostMapping("/changeDefault")
    public ResponseEntity<?> changeDefault(
            @RequestPart(value = "userId") Integer userId,
            @RequestPart(value = "shipLocationId") Integer shipLocationId) {
        shipLocationServiceImp.changeDefault(userId, shipLocationId);
        return new ResponseEntity<>("change default shipLocation success", HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody ShipLocationDTO shipLocationDTO) {
        shipLocationServiceImp.update(shipLocationDTO);
        return new ResponseEntity<>("update shipLocation success", HttpStatus.OK);
    }

    @PostMapping("/hide/{id}")
    public ResponseEntity<?> hide(@PathVariable Integer id) {
        shipLocationServiceImp.hide(id);
        return new ResponseEntity<>("hide shipLocation success", HttpStatus.OK);
    }

    @PostMapping("/show/{id}")
    public ResponseEntity<?> show(@PathVariable Integer id) {
        shipLocationServiceImp.show(id);
        return new ResponseEntity<>("show shipLocation success", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        shipLocationServiceImp.delete(id);
        return new ResponseEntity<>("delete shipLocation success", HttpStatus.OK);
    }
}
