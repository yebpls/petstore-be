package com.bc03capstone.bc03cs.service.imp;


import org.springframework.web.multipart.MultipartFile;

public interface PetImageServiceImp {
    void addPetImage(MultipartFile imageUrl, Integer petId);
}
