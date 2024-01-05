package com.bc03capstone.bc03cs.controller;

import com.bc03capstone.bc03cs.entity.Species;
import com.bc03capstone.bc03cs.service.imp.SpeciesServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@SecurityRequirement(name = "Authorization")

@CrossOrigin
public class TestController {
    @Operation(summary = "Test API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = " Bad Request!"),
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @GetMapping("")
    public ResponseEntity<?> Test() {
        Species species = new Species();
        species.setId(1);

        return new ResponseEntity<>("test", HttpStatus.OK);
    }
}
