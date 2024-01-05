package com.bc03capstone.bc03cs.service.imp;

import com.bc03capstone.bc03cs.DTO.PetDTO;
import com.bc03capstone.bc03cs.entity.Pet;

import java.util.List;

public interface PetServiceImp {
    List<PetDTO> getAllPet();
    PetDTO convertToDTO(Pet pet);
}
