package com.bc03capstone.bc03cs.controller;

import com.bc03capstone.bc03cs.jwt.JwtHelper;
import com.bc03capstone.bc03cs.payload.BaseResponse;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwtHelper;
    private Logger logger = LoggerFactory.getLogger(LoginController.class); //LoginController.class là tên file và pakage
    private Gson gson = new Gson();
    @PostMapping("")
    public ResponseEntity<?> login() {
        String data = jwtHelper.decodeToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJbe1wicm9sZVwiOlwiUk9MRV9BRE1JTlwifV0iLCJleHAiOjE3MDk1NDIxNzZ9.OHy4ZdESpe_vBwn5lMo2ZmpmsHiM-sNCeDeip84K8jc");
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam String email, @RequestParam String password) {
        logger.info("Username: " + email);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManager.authenticate(token);
        logger.info("authentication: " + authentication);
        String json = gson.toJson(authentication.getAuthorities());
        String jwtToken = jwtHelper.generateToken(json);
        logger.info("Response: " + jwtToken);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(jwtToken);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<?> check() {
        return new ResponseEntity<>("admin oke", HttpStatus.OK);
    }
}
