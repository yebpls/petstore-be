package com.bc03capstone.bc03cs.controller;

import com.bc03capstone.bc03cs.service.imp.FileServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FileServiceImp fileServiceImp;

    @GetMapping("/{fileName}")
    ResponseEntity<?> download(@PathVariable String fileName) {
        Resource resource = fileServiceImp.download(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + fileName + "\"").body(resource);
    }

    @PostMapping("/save")
    ResponseEntity<?> save( @RequestPart(value = "fileName") MultipartFile fileName) {
        fileServiceImp.save(fileName);
        logger.info("save file: " + fileName);
        return new ResponseEntity<>("save file success", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<?> delete(@PathVariable String fileName) {
        fileServiceImp.delete(fileName);
        logger.info("delete file: " + fileName);
        return new ResponseEntity<>("delete file success", HttpStatus.OK);
    }
}
