package com.bc03capstone.bc03cs.service.imp;

import com.bc03capstone.bc03cs.DTO.ShipLocationDTO;

import java.util.List;

public interface ShipLocationServiceImp {
    List<ShipLocationDTO> getAllByStatusAndUser(Integer userId);
    ShipLocationDTO getByStatusAndUserAndIsDefault(Integer userId);
    ShipLocationDTO getByStatusAndId(Integer id);
    void add(String address, String phoneNumber, Boolean isDefault, Integer userId);
    void changeDefault(Integer userId, Integer shipLocationId);
    void updateInformation(Integer id, String address, String phoneNumber);
    void hide(Integer id);
    void show(Integer id);
    void delete(Integer id);

}
