package com.bc03capstone.bc03cs.controller;

import com.bc03capstone.bc03cs.service.imp.PetServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/api/pet")
public class PetController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PetServiceImp petServiceImp;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(petServiceImp.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findAllBySpecies")
    public ResponseEntity<?> findAllBySpecies(@RequestParam Integer speciesId) {
        return new ResponseEntity<>(petServiceImp.findAllBySpecies(speciesId), HttpStatus.OK);
    }

    @GetMapping("/findById")
    public ResponseEntity<?> findById(@RequestParam Integer id) {
        return new ResponseEntity<>(petServiceImp.findById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(
            @RequestPart(value = "petDTO") String jsonString,
            @RequestPart(value = "mainImage") MultipartFile mainImage,
            @RequestPart(value = "imageUrlList") MultipartFile[] imageUrlList) {
        Integer id = petServiceImp.add(jsonString, mainImage, imageUrlList);
        logger.info("Add pet id: " + id);
        return new ResponseEntity<>("Add pet success", HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(
            @RequestPart(value = "petDTO") String jsonString,
            @RequestPart(value = "mainImage") MultipartFile mainImage) {
        Integer id = petServiceImp.update(jsonString, mainImage);
        logger.info("update pet id: " + id);
        return new ResponseEntity<>("update pet success", HttpStatus.OK);
    }

    @PostMapping("/sold/{id}")
    public ResponseEntity<?> sold(@PathVariable Integer id) {
        petServiceImp.sold(id);
        logger.info("sold pet id: " + id);
        return new ResponseEntity<>("sold pet success", HttpStatus.OK);
    }

    @PostMapping("/hide/{id}")
    public ResponseEntity<?> hide(@PathVariable Integer id) {
        petServiceImp.hide(id);
        logger.info("hide pet id: " + id);
        return new ResponseEntity<>("hide pet success", HttpStatus.OK);
    }

    @PostMapping("/show/{id}")
    public ResponseEntity<?> show(@PathVariable Integer id) {
        petServiceImp.show(id);
        logger.info("show pet id: " + id);
        return new ResponseEntity<>("show pet success", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        petServiceImp.delete(id);
        logger.info("delete pet id: " + id);
        return new ResponseEntity<>("delete pet success", HttpStatus.OK);
    }

}
