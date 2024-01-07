package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.repository.PetImageRepository;
import com.bc03capstone.bc03cs.service.imp.PetImageServiceImp;
import com.bc03capstone.bc03cs.service.imp.PetServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PetImageService implements PetImageServiceImp {
    @Autowired
    private PetImageRepository petImageRepository;
    @Autowired
    private PetServiceImp petServiceImp;
}
