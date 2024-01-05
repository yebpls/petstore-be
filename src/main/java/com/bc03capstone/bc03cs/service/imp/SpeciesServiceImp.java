package com.bc03capstone.bc03cs.service.imp;

import com.bc03capstone.bc03cs.DTO.SpeciesDTO;
import com.bc03capstone.bc03cs.entity.Species;

public interface SpeciesServiceImp {
    SpeciesDTO convertToDTO(Species species);
}
