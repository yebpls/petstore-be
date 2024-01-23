package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.service.imp.FileServiceImp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService implements FileServiceImp {
   @Value("${root.path.upload}")
    private String root;

   @Override
    public void save(MultipartFile file) {
       Path rootPath = Paths.get(root);
       try {
           if(!Files.exists(rootPath)) {
               Files.createDirectories(rootPath);
           }
           Files.copy(file.getInputStream(),rootPath.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
       } catch (Exception e) {
           throw new RuntimeException("Error upload file " +e.getMessage());
       }
   }

    @Override
    public Resource download(String fileName) {
        Path rootPath = Paths.get(root);
       try {
           Path pathImage = rootPath.resolve(fileName);
           Resource resource = new UrlResource(pathImage.toUri());
           if(resource.exists()|| resource.isReadable()) {
               return resource;
           } else {
               throw new RuntimeException("Error can't find file or can't read file");
           }
       } catch (Exception e) {
           throw new RuntimeException("Error can't find file " + e.getMessage());
       }
    }
    public void delete(String fileName) {
        Path rootPath = Paths.get(root);
        Path filePath = rootPath.resolve(fileName);
        try {
            if (Files.exists(filePath)) {
                Files.delete(filePath);  //delete forever
            } else {
                throw new RuntimeException("Error: File not found");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error deleting file: " + e.getMessage());
        }
    }
}
