package com.bc03capstone.bc03cs.controller;

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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PetImageServiceImp petImageServiceImp;

    @GetMapping("/findAllByPet")
    public ResponseEntity<?> findAllByPet(@RequestParam Integer petId) {
        return new ResponseEntity<>(petImageServiceImp.findAllByPet(petId), HttpStatus.OK);
    }

    @GetMapping("/findById")
    public ResponseEntity<?> findById(@RequestParam Integer id) {
        return new ResponseEntity<>(petImageServiceImp.findById(id), HttpStatus.OK);
    }

    @PostMapping("/add/{petId}")
    public ResponseEntity<?> add(
            @PathVariable Integer petId,
            @RequestPart(value = "imageUrl") MultipartFile imageUrl) {
        Integer id = petImageServiceImp.add(petId, imageUrl);
        logger.info("add petImage id: " + id);
        return new ResponseEntity<>("Add petImage success", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        petImageServiceImp.delete(id);
        logger.info("delete petImage id: " + id);
        return new ResponseEntity<>("delete petImage success", HttpStatus.OK);
    }
}
