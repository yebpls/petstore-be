package com.bc03capstone.bc03cs.controller;

import com.bc03capstone.bc03cs.DTO.SpeciesDTO;
import com.bc03capstone.bc03cs.service.imp.SpeciesServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/species")
public class SpeciesController {
    @Autowired
    private SpeciesServiceImp speciesServiceImp;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(speciesServiceImp.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findById")
    public ResponseEntity<?> findById(@RequestParam Integer id) {
        return new ResponseEntity<>(speciesServiceImp.findById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody SpeciesDTO speciesDTO) {
        speciesServiceImp.add(speciesDTO);
        return new ResponseEntity<>("Add species success", HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody SpeciesDTO speciesDTO) {
        speciesServiceImp.update(speciesDTO);
        return new ResponseEntity<>("update species success", HttpStatus.OK);
    }

    @PostMapping("/hide/{id}")
    public ResponseEntity<?> hide(@PathVariable Integer id) {
        speciesServiceImp.hide(id);
        return new ResponseEntity<>("hide species success", HttpStatus.OK);
    }

    @PostMapping("/show/{id}")
    public ResponseEntity<?> show(@PathVariable Integer id) {
        speciesServiceImp.show(id);
        return new ResponseEntity<>("show species success", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        speciesServiceImp.delete(id);
        return new ResponseEntity<>("delete Species success", HttpStatus.OK);
    }
}
