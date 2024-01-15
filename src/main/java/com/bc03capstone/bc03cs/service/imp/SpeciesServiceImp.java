package com.bc03capstone.bc03cs.service.imp;

import com.bc03capstone.bc03cs.DTO.SpeciesDTO;

import java.util.List;

public interface SpeciesServiceImp {
    List<SpeciesDTO> getAllByStatus();

    SpeciesDTO getByStatusAndId(Integer id);

    void add(String name);

    void update(Integer id, String name);

    void hide(Integer id);

    void show(Integer id);

    void delete(Integer id);
}
