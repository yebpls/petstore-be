package com.bc03capstone.bc03cs.controller;

import com.bc03capstone.bc03cs.DTO.PetDTO;
import com.bc03capstone.bc03cs.payload.BaseResponse;
import com.bc03capstone.bc03cs.service.imp.PetServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/pet")
public class PetController {
    private Logger logger = LoggerFactory.getLogger(PetController.class);
    @Autowired
    private PetServiceImp petServiceImp;
    @GetMapping("")
    public ResponseEntity<?> getAllProduct() {
            List<PetDTO> listPetDTO = petServiceImp.getAllPet();
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setData(listPetDTO);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
