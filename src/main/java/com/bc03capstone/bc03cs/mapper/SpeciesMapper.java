package com.bc03capstone.bc03cs.mapper;

import com.bc03capstone.bc03cs.DTO.SpeciesDTO;
import com.bc03capstone.bc03cs.entity.Species;
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

    public Species revertToEntity(SpeciesDTO speciesDTO) {
        Species species = modelMapper.map(speciesDTO, Species.class);
        species.setStatus(true);
        return species;
    }
}
