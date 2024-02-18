package com.bc03capstone.bc03cs.mapper;

import com.bc03capstone.bc03cs.DTO.PetDTO;
import com.bc03capstone.bc03cs.entity.Pet;
import com.bc03capstone.bc03cs.entity.Species;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public Pet revertToEntity(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            PetDTO petDTO = objectMapper.readValue(jsonString, PetDTO.class);
            Pet pet = modelMapper.map(petDTO, Pet.class);
            Species species = new Species();
            species.setId(petDTO.getSpeciesID());
            pet.setSpecies(species);
            pet.setIsSold(false);
            pet.setStatus(true);
            return pet;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error revertToEntity Pet: " + e.getMessage());
        }
    }
}
