package com.bc03capstone.bc03cs.mapper;

import com.bc03capstone.bc03cs.DTO.PetDTO;
import com.bc03capstone.bc03cs.entity.Pet;
import com.bc03capstone.bc03cs.entity.Species;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PetMapper {
    @Autowired
    private ModelMapper modelMapper;

    public PetDTO convertToDTO(Pet pet) {
        PetDTO petDTO = modelMapper.map(pet, PetDTO.class);
        petDTO.setSpeciesID(pet.getSpecies().getId());
        return petDTO;
    }

    public Pet revertToEntity(PetDTO petDTO){
        Pet pet = modelMapper.map(petDTO, Pet.class);
        Species species = new Species();
        species.setId(petDTO.getSpeciesID());
        pet.setSpecies(species);
        pet.setIsSold(false);
        pet.setStatus(true);
        return pet;
    }
}
