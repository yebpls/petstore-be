package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.DTO.PetImageDTO;
import com.bc03capstone.bc03cs.entity.PetImage;
import com.bc03capstone.bc03cs.repository.PetImageRepository;
import com.bc03capstone.bc03cs.service.imp.PetImageServiceImp;
import com.bc03capstone.bc03cs.service.imp.PetServiceImp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class PetImageService implements PetImageServiceImp {
    @Autowired
    private PetImageRepository petImageRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PetServiceImp petServiceImp;
    @Value("${domain}")
    private String domain;
    @Override
    public PetImageDTO convertToDTO(PetImage petImage) {
        PetImageDTO petImageDTO = modelMapper.map(petImage,PetImageDTO.class);
        petImageDTO.setImageUrl(domain + "/file/" + petImage.getImageUrl());
        petImageDTO.setPetDTO(petServiceImp.convertToDTO(petImage.getPet()));
        return petImageDTO;
    }
}
