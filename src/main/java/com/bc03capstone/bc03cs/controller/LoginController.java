package com.bc03capstone.bc03cs.controller;

import com.bc03capstone.bc03cs.DTO.SignInRequest;
import com.bc03capstone.bc03cs.entity.User;
import com.bc03capstone.bc03cs.jwt.JwtHelper;
import com.bc03capstone.bc03cs.payload.BaseResponse;
import com.bc03capstone.bc03cs.repository.PetRepository;
import com.bc03capstone.bc03cs.service.LoginService;
import com.bc03capstone.bc03cs.service.imp.LoginServiceImp;
import com.google.gson.Gson;
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
    private LoginServiceImp loginServiceImp;

    @Autowired
    private JwtHelper jwtHelper;
    private Logger logger = LoggerFactory.getLogger(LoginController.class);
    private Gson gson = new Gson();
    @PostMapping("")
    public ResponseEntity<?> login() {
        String data = jwtHelper.decodeToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJbe1wicm9sZVwiOlwiUk9MRV9BRE1JTlwifV0iLCJleHAiOjE3MDk1NDIxNzZ9.OHy4ZdESpe_vBwn5lMo2ZmpmsHiM-sNCeDeip84K8jc");
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SignInRequest signInRequest) {
        String email = signInRequest.getEmail();
        String password = signInRequest.getPassword();
        logger.info("Username: " + email);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManager.authenticate(token);
        logger.info("authentication: " + authentication);
        User user = loginServiceImp.checkLogin(email, password);
        String json = gson.toJson(authentication.getAuthorities());
        String jwtToken = jwtHelper.generateToken(json, user.getRole(), user.getId());
        logger.info("Response: " + jwtToken);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(jwtToken);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
