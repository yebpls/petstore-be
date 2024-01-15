package com.bc03capstone.bc03cs.controller;

import com.bc03capstone.bc03cs.service.imp.PetImageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/api/petImage")
public class PetImageController {
    @Autowired
    private PetImageServiceImp petImageServiceImp;

    @GetMapping("/findAllByPetId")
    public ResponseEntity<?> getAllByStatusAndPetId(@RequestParam Integer petId) {
        return new ResponseEntity<>(petImageServiceImp.getAllByStatusAndPetId(petId), HttpStatus.OK);
    }

    @GetMapping("/findById")
    public ResponseEntity<?> getByStatusAndId(@RequestParam Integer id) {
        return new ResponseEntity<>(petImageServiceImp.getByStatusAndId(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(
            @RequestPart(value = "imageUrl") MultipartFile imageUrl,
            @RequestPart(value = "petId") Integer petId) {
        petImageServiceImp.add(imageUrl, petId);
        return new ResponseEntity<>("Add petImage success", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        petImageServiceImp.delete(id);
        return new ResponseEntity<>("delete petImage success", HttpStatus.OK);
    }
}
