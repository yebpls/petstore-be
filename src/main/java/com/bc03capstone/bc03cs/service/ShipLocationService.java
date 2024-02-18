package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.DTO.ShipLocationDTO;
import com.bc03capstone.bc03cs.entity.ShipLocation;
import com.bc03capstone.bc03cs.entity.User;
import com.bc03capstone.bc03cs.mapper.ShipLocationMapper;
import com.bc03capstone.bc03cs.repository.OrdersRepository;
import com.bc03capstone.bc03cs.repository.ShipLocationRepository;
import com.bc03capstone.bc03cs.repository.UserRepository;
import com.bc03capstone.bc03cs.service.imp.ShipLocationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShipLocationService implements ShipLocationServiceImp {
    @Autowired
    private ShipLocationRepository shipLocationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ShipLocationMapper shipLocationMapper;

    @Override
    public List<ShipLocationDTO> findAllByUser(Integer userId) {
        User user = userRepository.findByIdAndStatus(userId,true);
        return shipLocationRepository.findAllByUserAndStatus(user,true)
                .stream().map(shipLocationMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public ShipLocationDTO findByUserAndIsDefault(Integer userId) {
        User user = userRepository.findByIdAndStatus(userId,true);
        ShipLocation shipLocation = shipLocationRepository.findByUserAndIsDefaultAndStatus(user,true,true);
        return shipLocationMapper.convertToDTO(shipLocation);
    }

    @Override
    public ShipLocationDTO findById(Integer id) {
        ShipLocation shipLocation = shipLocationRepository.findByIdAndStatus(id,true);
        return shipLocationMapper.convertToDTO(shipLocation);
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public Integer add(String jsonString) {
        ShipLocation newShipLocation = shipLocationMapper.revertToEntity(jsonString);
        if (newShipLocation.getIsDefault()) offDefault(newShipLocation.getUser().getId());
        try {
            shipLocationRepository.save(newShipLocation);
            return newShipLocation.getId();
        } catch (Exception e) {
            throw new RuntimeException("Error add shipLocation " + e.getMessage());
        }
    }

    private void offDefault(Integer userId) {
        User user = userRepository.findByIdAndStatus(userId,true);
        ShipLocation shipLocation = shipLocationRepository.findByUserAndIsDefaultAndStatus(user,true,true);
        shipLocation.setIsDefault(false);
        try {
            shipLocationRepository.save(shipLocation);
        } catch (Exception e) {
            throw new RuntimeException("Error offDefault " + e.getMessage());
        }
    }

    @Override
    public void changeDefault(Integer userId, Integer shipLocationId) {
        offDefault(userId);
        ShipLocation shipLocation = shipLocationRepository.findByIdAndStatus(shipLocationId,true);
        shipLocation.setIsDefault(true);
        try {
            shipLocationRepository.save(shipLocation);
        } catch (Exception e) {
            throw new RuntimeException("Error changeDefault " + e.getMessage());
        }
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public Integer update(String jsonString) {
        try {
            ShipLocation newShipLocation = shipLocationMapper.revertToEntity(jsonString);
            ShipLocation oldShipLocation = shipLocationRepository.findByIdAndStatus(newShipLocation.getId(), true);
            if (oldShipLocation.getIsDefault()) newShipLocation.setIsDefault(true);
            if (newShipLocation.getIsDefault()) offDefault(newShipLocation.getUser().getId());
            if (ordersRepository.findByShipLocation(oldShipLocation)!=null) {
                //an order used this shipLocation so have to create new shipLocation
                newShipLocation.setId(null);
                oldShipLocation.setStatus(false);
                if (oldShipLocation.getIsDefault()) oldShipLocation.setIsDefault(false);
                shipLocationRepository.save(oldShipLocation);
            }
            shipLocationRepository.save(newShipLocation);
            return newShipLocation.getId();
        } catch (Exception e) {
            throw new RuntimeException("Error update shipLocation " + e.getMessage());
        }
    }

    @Override
    public void hide(Integer id) {
        ShipLocation shipLocation = shipLocationRepository.findByIdAndStatus(id,true);
        shipLocation.setStatus(false);
        shipLocationRepository.save(shipLocation);
    }

    @Override
    public void show(Integer id) {
        ShipLocation shipLocation = shipLocationRepository.findByIdAndStatus(id,false);
        shipLocation.setStatus(true);
        shipLocationRepository.save(shipLocation);
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void delete(Integer id) {
        ShipLocation shipLocation = shipLocationRepository.findById(id).orElseThrow();
        if (ordersRepository.findByShipLocation(shipLocation)==null) { //no order use this shipLocation
            shipLocationRepository.deleteById(id);
        } else {
            hide(id);
        }
    }
}
