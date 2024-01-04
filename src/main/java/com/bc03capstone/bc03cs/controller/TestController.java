package com.bc03capstone.bc03cs.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@CrossOrigin
public class TestController {
  @GetMapping("")
  public ResponseEntity<?> Test() {


    return new ResponseEntity<>("test", HttpStatus.OK);
  }
}
