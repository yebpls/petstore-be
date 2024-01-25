package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.DTO.SpeciesDTO;
import com.bc03capstone.bc03cs.entity.Species;
import com.bc03capstone.bc03cs.mapper.SpeciesMapper;
import com.bc03capstone.bc03cs.repository.SpeciesRepository;
import com.bc03capstone.bc03cs.service.imp.PetServiceImp;
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
    private PetServiceImp petServiceImp;

    @Override
    public List<SpeciesDTO> findAll() {
        return speciesRepository.findAllByStatus(true)
                .stream().map(speciesMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public SpeciesDTO findById(Integer id) {
        Species species = speciesRepository.findByIdAndStatus(id,true);
        return speciesMapper.convertToDTO(species);
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void add(SpeciesDTO speciesDTO) {
        Species newSpecies = speciesMapper.revertToEntity(speciesDTO);
        try {
            speciesRepository.save(newSpecies);
        } catch (Exception e) {
            throw new RuntimeException("Error add species " + e.getMessage());
        }
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void update(SpeciesDTO speciesDTO) {
        Species species = speciesMapper.revertToEntity(speciesDTO);
        try {
            speciesRepository.save(species);
        } catch (Exception e) {
            throw new RuntimeException("Error update species " + e.getMessage());
        }
    }

    @Override
    public void hide(Integer id) {
        Species species = speciesRepository.findByIdAndStatus(id,true);
        species.setStatus(false);
        speciesRepository.save(species);
    }

    @Override
    public void show(Integer id) {
        Species species = speciesRepository.findByIdAndStatus(id,false);
        species.setStatus(true);
        speciesRepository.save(species);
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void delete(Integer id) {
        Species species = speciesRepository.findById(id).orElseThrow();
        species.getPetList().forEach(item -> petServiceImp.delete(item.getId()));
        speciesRepository.deleteById(id);
    }
}
