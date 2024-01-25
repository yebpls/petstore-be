package com.bc03capstone.bc03cs.controller;

import com.bc03capstone.bc03cs.DTO.UserDTO;
import com.bc03capstone.bc03cs.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserServiceImp userServiceImp;

    @GetMapping("/findById")
    public ResponseEntity<?> findById(@RequestParam Integer id) {
        return new ResponseEntity<>(userServiceImp.findById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(
            @RequestPart(value = "userDTO") UserDTO userDTO,
            @RequestPart(value = "avatarUrl") MultipartFile avatarUrl) {
        userServiceImp.add(userDTO, avatarUrl);
        return new ResponseEntity<>("Add user success", HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(
            @RequestPart(value = "userDTO") UserDTO userDTO,
            @RequestPart(value = "avatarUrl") MultipartFile avatarUrl) {
        userServiceImp.update(userDTO, avatarUrl);
        return new ResponseEntity<>("update user success", HttpStatus.OK);
    }

    @PostMapping("/hide/{id}")
    public ResponseEntity<?> hide(@PathVariable Integer id) {
        userServiceImp.hide(id);
        return new ResponseEntity<>("hide user success", HttpStatus.OK);
    }

    @PostMapping("/show/{id}")
    public ResponseEntity<?> show(@PathVariable Integer id) {
        userServiceImp.show(id);
        return new ResponseEntity<>("show user success", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        userServiceImp.delete(id);
        return new ResponseEntity<>("delete user success", HttpStatus.OK);
    }
}