package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.DTO.SpeciesDTO;
import com.bc03capstone.bc03cs.entity.Species;
import com.bc03capstone.bc03cs.mapper.SpeciesMapper;
import com.bc03capstone.bc03cs.repository.SpeciesRepository;
import com.bc03capstone.bc03cs.service.imp.PetServiceImp;
import com.bc03capstone.bc03cs.service.imp.SpeciesServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
    private PetServiceImp petServiceImp;

    @Cacheable("speciesList")
    @Override
    public List<SpeciesDTO> findAll() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
        return speciesRepository.findAllByStatus(true)
                .stream().map(speciesMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public SpeciesDTO findById(Integer id) {
        Species species = speciesRepository.findByIdAndStatus(id,true);
        return speciesMapper.convertToDTO(species);
    }

    @CacheEvict(value = "speciesList", allEntries = true)
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public Integer add(String jsonString) {
        Species newSpecies = speciesMapper.revertToEntity(jsonString);
        try {
            speciesRepository.save(newSpecies);
            return newSpecies.getId();
        } catch (Exception e) {
            throw new RuntimeException("Error add species " + e.getMessage());
        }
    }

    @CacheEvict(value = "speciesList", allEntries = true)
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public Integer update(String jsonString) {
        Species species = speciesMapper.revertToEntity(jsonString);
        try {
            speciesRepository.save(species);
            return species.getId();
        } catch (Exception e) {
            throw new RuntimeException("Error update species " + e.getMessage());
        }
    }

    @CacheEvict(value = "speciesList", allEntries = true)
    @Override
    public void hide(Integer id) {
        Species species = speciesRepository.findByIdAndStatus(id,true);
        species.getPetList().forEach(pet -> petServiceImp.hide(pet.getId()));
        species.setStatus(false);
        speciesRepository.save(species);
    }

    @CacheEvict(value = "speciesList", allEntries = true)
    @Override
    public void show(Integer id) {
        Species species = speciesRepository.findByIdAndStatus(id,false);
        species.getPetList().forEach(pet -> petServiceImp.show(pet.getId()));
        species.setStatus(true);
        speciesRepository.save(species);
    }

    @CacheEvict(value = "speciesList", allEntries = true)
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void delete(Integer id) {
        Species species = speciesRepository.findById(id).orElseThrow();
        species.getPetList().forEach(pet -> petServiceImp.delete(pet.getId()));
        speciesRepository.deleteById(id);
    }
}
