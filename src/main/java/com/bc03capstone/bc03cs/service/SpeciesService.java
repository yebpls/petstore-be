package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.repository.SpeciesRepository;
import com.bc03capstone.bc03cs.service.imp.SpeciesServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpeciesService implements SpeciesServiceImp {
    @Autowired
    private SpeciesRepository speciesRepository;
}
