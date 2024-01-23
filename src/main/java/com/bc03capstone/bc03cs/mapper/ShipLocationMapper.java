package com.bc03capstone.bc03cs.mapper;

import com.bc03capstone.bc03cs.DTO.ShipLocationDTO;
import com.bc03capstone.bc03cs.entity.ShipLocation;
import com.bc03capstone.bc03cs.entity.User;
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

    public ShipLocation revertToEntity(ShipLocationDTO shipLocationDTO) {
        ShipLocation shipLocation = modelMapper.map(shipLocationDTO, ShipLocation.class);
        User user = new User();
        user.setId(shipLocationDTO.getUserId());
        shipLocation.setUser(user);
        shipLocation.setStatus(true);
        return shipLocation;
    }
}
