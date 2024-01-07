package com.bc03capstone.bc03cs.mapper;

import com.bc03capstone.bc03cs.DTO.PetDTO;
import com.bc03capstone.bc03cs.entity.Pet;
import com.bc03capstone.bc03cs.entity.PetImage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PetMapper {
    @Autowired
    private ModelMapper modelMapper;
    public PetDTO convertToDTO(Pet pet) {
        PetDTO petDTO = modelMapper.map(pet, PetDTO.class);
        petDTO.setSpeciesID(pet.getSpecies().getId());
        petDTO.setPetImageIdList(pet.getPetImageList().stream().map(PetImage::getId).collect(Collectors.toList()));
        return petDTO;
    }
}
