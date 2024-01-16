package com.bc03capstone.bc03cs.controller;

import com.bc03capstone.bc03cs.service.imp.PetServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin
@RestController
@RequestMapping("/api/pet")
public class PetController {
    @Autowired
    private PetServiceImp petServiceImp;

    @CrossOrigin
    @GetMapping("")
    public ResponseEntity<?> getAllByStateAndStatus() {
        return new ResponseEntity<>(petServiceImp.getAllByStateAndStatus(), HttpStatus.OK);
    }

    @GetMapping("/findAllBySpecies")
    public ResponseEntity<?> getAllBySpeciesAndStateAndStatus(@RequestParam Integer speciesId) {
        return new ResponseEntity<>(petServiceImp.getAllBySpeciesAndStateAndStatus(speciesId), HttpStatus.OK);
    }

    @GetMapping("/findById")
    public ResponseEntity<?> getByStatusAndId(@RequestParam Integer id) {
        return new ResponseEntity<>(petServiceImp.getByStatusAndId(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(
            @RequestPart(value = "breed") String breed,
            @RequestPart(value = "listPrice") String listPrice,
            @RequestPart(value = "salePercent") Integer salePercent,
            @RequestPart(value = "taxIncluded") String taxIncluded,
            @RequestPart(value = "age") String age,
            @RequestPart(value = "gender") String gender,
            @RequestPart(value = "color") String color,
            @RequestPart(value = "weight") String weight,
            @RequestPart(value = "country") String country,
            @RequestPart(value = "description") String description,
            @RequestPart(value = "mainImage") MultipartFile mainImage,
            @RequestPart(value = "speciesID") Integer speciesId,
            @RequestPart(value = "imageUrlList") MultipartFile[] imageUrlList) {
        petServiceImp.add(breed, listPrice, salePercent, taxIncluded,
                age, gender, color, weight, country, description,
                mainImage, speciesId, imageUrlList);
        return new ResponseEntity<>("Add pet success", HttpStatus.OK);
    }

    @PostMapping("/updateMainImage")
    public ResponseEntity<?> updateMainImage(
            @RequestPart(value = "id") Integer id,
            @RequestPart(value = "mainImage") MultipartFile mainImage) {
        petServiceImp.updateMainImage(id, mainImage);
        return new ResponseEntity<>("update mainImage of pet success", HttpStatus.OK);
    }

    @PostMapping("/updateInformation")
    public ResponseEntity<?> updateInformation(
            @RequestPart(value = "id") Integer id,
            @RequestPart(value = "breed") String breed,
            @RequestPart(value = "listPrice") String listPrice,
            @RequestPart(value = "salePercent") Integer salePercent,
            @RequestPart(value = "taxIncluded") String taxIncluded,
            @RequestPart(value = "age") String age,
            @RequestPart(value = "gender") String gender,
            @RequestPart(value = "color") String color,
            @RequestPart(value = "weight") String weight,
            @RequestPart(value = "country") String country,
            @RequestPart(value = "description") String description,
            @RequestPart(value = "speciesID") Integer speciesId) {
        petServiceImp.updateInformation(id, breed, listPrice, salePercent, taxIncluded,
                age, gender, color, weight, country, description, speciesId);
        return new ResponseEntity<>("update Information of pet success", HttpStatus.OK);
    }

    @PostMapping("/hide/{id}")
    public ResponseEntity<?> hide(@PathVariable Integer id) {
        petServiceImp.hide(id);
        return new ResponseEntity<>("hide pet success", HttpStatus.OK);
    }

    @PostMapping("/show/{id}")
    public ResponseEntity<?> show(@PathVariable Integer id) {
        petServiceImp.show(id);
        return new ResponseEntity<>("show pet success", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        petServiceImp.delete(id);
        return new ResponseEntity<>("delete pet success", HttpStatus.OK);
    }

}
