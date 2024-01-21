package com.bc03capstone.bc03cs.controller;

import com.bc03capstone.bc03cs.service.ShipLocationService;
import com.bc03capstone.bc03cs.service.imp.ShipLocationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/api/shipLocation")
public class ShipLocationController {

    @Autowired
    private ShipLocationServiceImp shipLocationServiceImp;

    @GetMapping("/getAllByUser")
    public ResponseEntity<?> getAllByStatusAndUser(@RequestParam Integer userId) {
        return new ResponseEntity<>(shipLocationServiceImp.getAllByStatusAndUser(userId), HttpStatus.OK);
    }

    @GetMapping("/getByUserAndIsDefault")
    public ResponseEntity<?> getByStatusAndUserAndIsDefault(@RequestParam Integer userId) {
        return new ResponseEntity<>(shipLocationServiceImp.getByStatusAndUserAndIsDefault(userId), HttpStatus.OK);
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getByStatusAndId(@RequestParam Integer id) {
        return new ResponseEntity<>(shipLocationServiceImp.getByStatusAndId(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(
            @RequestPart(value = "address") String address,
            @RequestPart(value = "phoneNumber") String phoneNumber,
            @RequestPart(value = "isDefault") Boolean isDefault,
            @RequestPart(value = "userId") Integer userId) {
        shipLocationServiceImp.add(address, phoneNumber, isDefault, userId);
        return new ResponseEntity<>("Add shipLocation success", HttpStatus.OK);
    }

    @PostMapping("/changeDefault")
    public ResponseEntity<?> changeDefault(
            @RequestPart(value = "userId") Integer userId,
            @RequestPart(value = "shipLocationId") Integer shipLocationId) {
        shipLocationServiceImp.changeDefault(userId, shipLocationId);
        return new ResponseEntity<>("change default shipLocation success", HttpStatus.OK);
    }

    @PostMapping("/updateInformation")
    public ResponseEntity<?> updateInformation(
            @RequestPart(value = "id") Integer id,
            @RequestPart(value = "address") String address,
            @RequestPart(value = "phoneNumber") String phoneNumber) {
        shipLocationServiceImp.updateInformation(id, address, phoneNumber);
        return new ResponseEntity<>("update Information of shipLocation success", HttpStatus.OK);
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
