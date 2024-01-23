package com.bc03capstone.bc03cs.controller;

import com.bc03capstone.bc03cs.service.imp.FileServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileServiceImp fileServiceImp;

    @GetMapping("/download/{filename}")
    ResponseEntity<?> download(@PathVariable String filename) {
        Resource resource = fileServiceImp.download(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"").body(resource);
    }

    @PostMapping("/save")
    ResponseEntity<?> save( @RequestPart(value = "filename") MultipartFile filename) {
        fileServiceImp.save(filename);
        return new ResponseEntity<>("save file success", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<?> delete(@PathVariable String fileName) {
        fileServiceImp.delete(fileName);
        return new ResponseEntity<>("delete file success", HttpStatus.OK);
    }
}
