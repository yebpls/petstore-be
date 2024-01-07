package com.bc03capstone.bc03cs.controller;

import com.bc03capstone.bc03cs.payload.BaseResponse;
import com.bc03capstone.bc03cs.service.imp.PetImageServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/api/petImage")
public class PetImageController {
    private Logger logger = LoggerFactory.getLogger(PetImageController.class);
    @Autowired
    private PetImageServiceImp petImageServiceImp;
    private BaseResponse baseResponse = new BaseResponse();;
    @PostMapping("/add")
    public ResponseEntity<?> addPetImage(
            @RequestPart(value = "imageUrl") MultipartFile imageUrl,
            @RequestPart(value = "petId") Integer petId) {
        petImageServiceImp.addPetImage(imageUrl, petId);
        baseResponse.setMessage("Add petImage success");
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
