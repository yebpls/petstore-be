package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.DTO.PetDTO;
import com.bc03capstone.bc03cs.DTO.ShipLocationDTO;
import com.bc03capstone.bc03cs.entity.Pet;
import com.bc03capstone.bc03cs.entity.ShipLocation;
import com.bc03capstone.bc03cs.entity.Species;
import com.bc03capstone.bc03cs.entity.User;
import com.bc03capstone.bc03cs.mapper.ShipLocationMapper;
import com.bc03capstone.bc03cs.repository.OrdersRepository;
import com.bc03capstone.bc03cs.repository.ShipLocationRepository;
import com.bc03capstone.bc03cs.repository.UserRepository;
import com.bc03capstone.bc03cs.service.imp.ShipLocationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
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
    public List<ShipLocationDTO> getAllByStatusAndUser(Integer userId) {
        User user = userRepository.findByStatusAndId(true, userId);
        return shipLocationRepository.findAllByStatusAndUser(true, user)
                .stream().map(shipLocationMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public ShipLocationDTO getByStatusAndUserAndIsDefault(Integer userId) {
        User user = userRepository.findByStatusAndId(true, userId);
        ShipLocation shipLocation = shipLocationRepository.findByStatusAndUserAndIsDefault(true, user,true);
        return shipLocationMapper.convertToDTO(shipLocation);
    }

    @Override
    public ShipLocationDTO getByStatusAndId(Integer id) {
        ShipLocation shipLocation = shipLocationRepository.findByStatusAndId(true, id);
        return shipLocationMapper.convertToDTO(shipLocation);
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void add(String address, String phoneNumber, Boolean isDefault, Integer userId) {
        ShipLocation newShiplocation = new ShipLocation();
        newShiplocation.setAddress(address);
        newShiplocation.setPhoneNumber(phoneNumber);

        if (isDefault) offDefault(userId);
        newShiplocation.setIsDefault(isDefault);

        User user = new User();
        user.setId(userId);
        newShiplocation.setUser(user);
        try {
            shipLocationRepository.save(newShiplocation);
        } catch (Exception e) {
            throw new RuntimeException("Error add shipLocation " + e.getMessage());
        }
    }

    private void offDefault(Integer userId) {
        User user = userRepository.findByStatusAndId(true, userId);
        shipLocationRepository.findByStatusAndUserAndIsDefault(true, user,true).setIsDefault(false);
    }
    @Override
    public void changeDefault(Integer userId, Integer shipLocationId) {
        offDefault(userId);
        shipLocationRepository.findByStatusAndId(true, shipLocationId).setIsDefault(true);
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void updateInformation(Integer id, String address, String phoneNumber) {
        ShipLocation shipLocation = shipLocationRepository.findByStatusAndId(true, id);
        if (ordersRepository.findByShipLocation(shipLocation)==null) { //no order use this shipLocation
            shipLocation.setAddress(address);
            shipLocation.setPhoneNumber(phoneNumber);
            try {
                shipLocationRepository.save(shipLocation);
            } catch (Exception e) {
                throw new RuntimeException("Error update information shipLocation " + e.getMessage());
            }
        } else {
            add(address,phoneNumber,shipLocation.getIsDefault(),shipLocation.getUser().getId());
        }
    }

    @Override
    public void hide(Integer id) {
        ShipLocation shipLocation = shipLocationRepository.findByStatusAndId(true, id);
        shipLocation.setStatus(false);
        shipLocationRepository.save(shipLocation);
    }

    @Override
    public void show(Integer id) {
        ShipLocation shipLocation = shipLocationRepository.findByStatusAndId(false, id);
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
