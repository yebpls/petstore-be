package com.bc03capstone.bc03cs.mapper;

import com.bc03capstone.bc03cs.DTO.SpeciesDTO;
import com.bc03capstone.bc03cs.entity.Pet;
import com.bc03capstone.bc03cs.entity.Species;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SpeciesMapper {
    @Autowired
    private ModelMapper modelMapper;
    public SpeciesDTO convertToDTO(Species species) {
        SpeciesDTO speciesDTO = modelMapper.map(species, SpeciesDTO.class);
        speciesDTO.setPetIdList(species.getPetList().stream().map(Pet::getId).collect(Collectors.toList()));
        return speciesDTO;
    }
}
