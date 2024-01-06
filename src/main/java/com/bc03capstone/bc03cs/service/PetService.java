package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.DTO.PetDTO;
import com.bc03capstone.bc03cs.DTO.PetImageDTO;
import com.bc03capstone.bc03cs.DTO.SpeciesDTO;
import com.bc03capstone.bc03cs.entity.Pet;
import com.bc03capstone.bc03cs.entity.PetImage;
import com.bc03capstone.bc03cs.entity.Species;
import com.bc03capstone.bc03cs.repository.PetRepository;
import com.bc03capstone.bc03cs.service.imp.PetImageServiceImp;
import com.bc03capstone.bc03cs.service.imp.PetServiceImp;
import com.bc03capstone.bc03cs.service.imp.SpeciesServiceImp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService implements PetServiceImp {

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private SpeciesServiceImp speciesServiceImp;
    @Autowired
    private PetImageServiceImp petImageServiceImp;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<PetDTO> getAllPet() {
        return petRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    @Override
    public PetDTO convertToDTO(Pet pet) {
        PetDTO petDTO = modelMapper.map(pet,PetDTO.class);
        petDTO.setSpeciesDTO(speciesServiceImp.convertToDTO(pet.getSpecies()));
        petDTO.setPetImageDTOList(pet.getPetImageList().stream().map(petImageServiceImp::convertToDTO).collect(Collectors.toList()));
        return petDTO;
    }
}
