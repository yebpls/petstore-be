package com.bc03capstone.bc03cs.mapper;

import com.bc03capstone.bc03cs.DTO.ShipLocationDTO;
import com.bc03capstone.bc03cs.entity.ShipLocation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ShipLocationMapper {
    @Autowired
    private ModelMapper modelMapper;
    public ShipLocationDTO convertToDTO(ShipLocation shipLocation) {
        ShipLocationDTO shipLocationDTO = modelMapper.map(shipLocation, ShipLocationDTO.class);
        shipLocationDTO.setUserId(shipLocation.getUser().getId());
        return shipLocationDTO;
    }
}
