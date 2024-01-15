package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.DTO.SpeciesDTO;
import com.bc03capstone.bc03cs.entity.Pet;
import com.bc03capstone.bc03cs.entity.Species;
import com.bc03capstone.bc03cs.mapper.SpeciesMapper;
import com.bc03capstone.bc03cs.repository.PetRepository;
import com.bc03capstone.bc03cs.repository.SpeciesRepository;
import com.bc03capstone.bc03cs.service.imp.SpeciesServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpeciesService implements SpeciesServiceImp {
    @Autowired
    private SpeciesRepository speciesRepository;
    @Autowired
    SpeciesMapper speciesMapper;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private PetService petService;

    @Override
    public List<SpeciesDTO> getAllByStatus() {
        return speciesRepository.findAllByStatus(true)
                .stream().map(speciesMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public SpeciesDTO getByStatusAndId(Integer id) {
        Species species = speciesRepository.findByStatusAndId(true, id);
        return speciesMapper.convertToDTO(species);
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void add(String name) {
        Species newSpecies = new Species();
        newSpecies.setName(name);
        newSpecies.setStatus(true);
        try {
            speciesRepository.save(newSpecies);
        } catch (Exception e) {
            throw new RuntimeException("Error add species " + e.getMessage());
        }
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void update(Integer id, String name) {
        Species species = speciesRepository.findByStatusAndId(true, id);
        species.setName(name);
        try {
            speciesRepository.save(species);
        } catch (Exception e) {
            throw new RuntimeException("Error update species " + e.getMessage());
        }
    }

    @Override
    public void hide(Integer id) {
        Species species = speciesRepository.findByStatusAndId(true, id);
        species.setStatus(false);
        speciesRepository.save(species);
    }

    @Override
    public void show(Integer id) {
        Species species = speciesRepository.findByStatusAndId(false, id);
        species.setStatus(true);
        speciesRepository.save(species);
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void delete(Integer id) {
        Species species = speciesRepository.findById(id).orElseThrow();
        species.getPetList().forEach(item -> petService.delete(item.getId()));
        speciesRepository.deleteById(id);
    }
}
