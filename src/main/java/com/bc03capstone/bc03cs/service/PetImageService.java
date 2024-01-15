package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.DTO.PetImageDTO;
import com.bc03capstone.bc03cs.entity.Pet;
import com.bc03capstone.bc03cs.entity.PetImage;
import com.bc03capstone.bc03cs.mapper.PetImageMapper;
import com.bc03capstone.bc03cs.repository.PetImageRepository;
import com.bc03capstone.bc03cs.repository.PetRepository;
import com.bc03capstone.bc03cs.service.imp.FileServiceImp;
import com.bc03capstone.bc03cs.service.imp.PetImageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PetImageService implements PetImageServiceImp {
    @Autowired
    private PetImageRepository petImageRepository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private FileServiceImp fileServiceImp;
    @Autowired
    private PetImageMapper petImageMapper;

    @Override
    public List<PetImageDTO> getAllByStatusAndPetId(Integer petId) {
        Pet pet = petRepository.findByStatusAndId(true, petId);
        return petImageRepository.findAllByStatusAndPet(true, pet)
                .stream().map(petImageMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public PetImageDTO getByStatusAndId(Integer id) {
        PetImage petImage = petImageRepository.findByStatusAndId(true, id);
        return petImageMapper.convertToDTO(petImage);
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void add(MultipartFile imageUrl, Integer petId) {
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
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void delete(Integer id) {
        PetImage petImage = petImageRepository.findById(id).orElseThrow();
//        fileServiceImp.delete(petImage.getImageUrl());
        petImageRepository.delete(petImage);
    }

}
