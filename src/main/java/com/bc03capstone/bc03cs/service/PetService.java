package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.DTO.PetDTO;
import com.bc03capstone.bc03cs.mapper.PetMapper;
import com.bc03capstone.bc03cs.repository.PetRepository;
import com.bc03capstone.bc03cs.service.imp.PetImageServiceImp;
import com.bc03capstone.bc03cs.service.imp.PetServiceImp;
import com.bc03capstone.bc03cs.service.imp.SpeciesServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private PetMapper petMapper;
    @Override
    public List<PetDTO> getAllPet() {
        return petRepository.findAll().stream().map(petMapper::convertToDTO).collect(Collectors.toList());
    }

}
