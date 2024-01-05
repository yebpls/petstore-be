package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.DTO.SpeciesDTO;
import com.bc03capstone.bc03cs.entity.Species;
import com.bc03capstone.bc03cs.repository.SpeciesRepository;
import com.bc03capstone.bc03cs.service.imp.PetServiceImp;
import com.bc03capstone.bc03cs.service.imp.SpeciesServiceImp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SpeciesService implements SpeciesServiceImp {
    @Autowired
    private SpeciesRepository speciesRepository;
    @Autowired
    private PetServiceImp petServiceImp;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public SpeciesDTO convertToDTO(Species species) {
        SpeciesDTO speciesDTO = modelMapper.map(species,SpeciesDTO.class);
        speciesDTO.setPetDTOList(species.getPetList().stream().map(petServiceImp::convertToDTO).collect(Collectors.toList()));
        return speciesDTO;
    }
}
