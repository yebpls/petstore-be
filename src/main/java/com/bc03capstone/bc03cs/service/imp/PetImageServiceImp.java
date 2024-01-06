package com.bc03capstone.bc03cs.service.imp;

import com.bc03capstone.bc03cs.DTO.PetImageDTO;
import com.bc03capstone.bc03cs.entity.PetImage;


public interface PetImageServiceImp {
    PetImageDTO convertToDTO(PetImage petImage);
}
