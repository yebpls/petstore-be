package com.bc03capstone.bc03cs.service.imp;

import com.bc03capstone.bc03cs.DTO.SpeciesDTO;

import java.util.List;

public interface SpeciesServiceImp {
    List<SpeciesDTO> findAll();
    SpeciesDTO findById(Integer id);
    Integer add(String jsonString);
    Integer update(String jsonString);
    void hide(Integer id);
    void show(Integer id);
    void delete(Integer id);
}
