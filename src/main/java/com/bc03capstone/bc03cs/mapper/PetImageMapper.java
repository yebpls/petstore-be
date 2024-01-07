package com.bc03capstone.bc03cs.mapper;

import com.bc03capstone.bc03cs.DTO.PetImageDTO;
import com.bc03capstone.bc03cs.entity.PetImage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PetImageMapper {
    @Autowired
    private ModelMapper modelMapper;
    @Value("${domain}")
    private String domain;
    public PetImageDTO convertToDTO(PetImage petImage) {
        PetImageDTO petImageDTO = modelMapper.map(petImage, PetImageDTO.class);
        petImageDTO.setImageUrl(domain + "/file/" + petImage.getImageUrl());
        petImageDTO.setPetId(petImage.getPet().getId());
        return petImageDTO;
    }
}