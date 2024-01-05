package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.repository.ShipLocationRepository;
import com.bc03capstone.bc03cs.service.imp.ShipLocationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShipLocationService implements ShipLocationServiceImp {

    @Autowired
    private ShipLocationRepository shipLocationRepository;
}
