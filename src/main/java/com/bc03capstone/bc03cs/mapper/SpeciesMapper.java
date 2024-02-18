package com.bc03capstone.bc03cs.mapper;

import com.bc03capstone.bc03cs.DTO.SpeciesDTO;
import com.bc03capstone.bc03cs.entity.Species;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpeciesMapper {
    @Autowired
    private ModelMapper modelMapper;

    public SpeciesDTO convertToDTO(Species species) {
        return modelMapper.map(species, SpeciesDTO.class);
    }

    public Species revertToEntity(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            SpeciesDTO speciesDTO = objectMapper.readValue(jsonString, SpeciesDTO.class);
            Species species = modelMapper.map(speciesDTO, Species.class);
            species.setStatus(true);
            return species;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error revertToEntity Species: " + e.getMessage());
        }
    }
}
