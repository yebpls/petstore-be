package com.bc03capstone.bc03cs.service.imp;

import com.bc03capstone.bc03cs.DTO.ShipLocationDTO;

import java.util.List;

public interface ShipLocationServiceImp {
    List<ShipLocationDTO> findAllByUser(Integer userId);
    ShipLocationDTO findByUserAndIsDefault(Integer userId);
    ShipLocationDTO findById(Integer id);
    Integer add(ShipLocationDTO shipLocationDTO);
    void changeDefault(Integer userId, Integer shipLocationId);
    void update(ShipLocationDTO shipLocationDTO);
    void hide(Integer id);
    void show(Integer id);
    void delete(Integer id);

}
