package com.bc03capstone.bc03cs.controller;

import com.bc03capstone.bc03cs.DTO.PetDTO;
import com.bc03capstone.bc03cs.payload.BaseResponse;
import com.bc03capstone.bc03cs.service.imp.PetServiceImp;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/pet")
public class PetController {
    private Logger logger = LoggerFactory.getLogger(PetController.class);
    @Autowired
    private PetServiceImp petServiceImp;
    private BaseResponse baseResponse = new BaseResponse();;
    @GetMapping("")
    public ResponseEntity<?> getAllProduct() {
        List<PetDTO> listPetDTO = petServiceImp.getAllPet();
        baseResponse.setData(listPetDTO);
        if (listPetDTO.isEmpty()) {
            baseResponse.setMessage("No pets found");
        }
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<?> getPetById(@Parameter(description = "Pet ID", example = "1") @PathVariable("id") Integer id) {
        PetDTO petDTO = petServiceImp.getPetById(id);
        baseResponse.setData(petDTO);
        if (petDTO == null) {
            baseResponse.setMessage("Pet not found");
        }
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<?> addPet(
            @RequestPart(value = "breed") String breed,
            @RequestPart(value = "listPrice") BigDecimal listPrice,
            @RequestPart(value = "salePercent") Integer salePercent,
            @RequestPart(value = "taxIncluded") BigDecimal taxIncluded,
            @RequestPart(value = "age") String age,
            @RequestPart(value = "gender") String gender,
            @RequestPart(value = "color") String color,
            @RequestPart(value = "weight") String weight,
            @RequestPart(value = "country") String country,
            @RequestPart(value = "description") String description,
            @RequestPart(value = "speciesID") Integer speciesID,
            @RequestPart(value = "imageUrlList") MultipartFile[] imageUrlList) {
        petServiceImp.addPet(breed, listPrice, salePercent, taxIncluded, age,
                gender, color, weight, country, description, speciesID, imageUrlList);
        baseResponse.setMessage("Add pet success");
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
