package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.entity.Pet;
import com.bc03capstone.bc03cs.entity.PetImage;
import com.bc03capstone.bc03cs.repository.PetImageRepository;
import com.bc03capstone.bc03cs.service.imp.FileServiceImp;
import com.bc03capstone.bc03cs.service.imp.PetImageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Service
public class PetImageService implements PetImageServiceImp {
    @Autowired
    private PetImageRepository petImageRepository;
    @Autowired
    private FileServiceImp fileServiceImp;
    @Transactional(rollbackFor = {RuntimeException.class,Exception.class})
    @Override
    public void addPetImage(MultipartFile imageUrl, Integer petId) {
        fileServiceImp.save(imageUrl);
        PetImage newPetImage = new PetImage();
        newPetImage.setImageUrl(imageUrl.getOriginalFilename());
        newPetImage.setStatus(true);
        Pet pet = new Pet();
        pet.setId(petId);
        newPetImage.setPet(pet);
        try {
            petImageRepository.save(newPetImage);
        } catch (Exception e) {
            throw new RuntimeException("Error add petImage " + e.getMessage());
        }
    }
}
