package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.DTO.UserDTO;
import com.bc03capstone.bc03cs.entity.User;
import com.bc03capstone.bc03cs.mapper.UserMapper;
import com.bc03capstone.bc03cs.repository.UserRepository;
import com.bc03capstone.bc03cs.service.imp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Service
public class UserService implements UserServiceImp {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FileServiceImp fileServiceImp;

    @Autowired
    private CartServiceImp cartServiceImp;

    @Autowired
    private ShipLocationServiceImp shipLocationServiceImp;

    @Autowired
    private OrdersServiceImp ordersServiceImp;

    @Override
    public UserDTO findById(Integer id) {
        User user = userRepository.findByIdAndStatus(id,true);
        return userMapper.convertToDTO(user);
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public Integer add(UserDTO userDTO, MultipartFile avatarUrl) {
        User newUser = userMapper.revertToEntity(userDTO);
        newUser.setCreateDate(LocalDate.now());
        newUser.setAvatarUrl(avatarUrl.getOriginalFilename());
        fileServiceImp.save(avatarUrl);
        try {
            userRepository.save(newUser);
            cartServiceImp.add(newUser);
            return newUser.getId();
        } catch (Exception e) {
            throw new RuntimeException("Error add user " + e.getMessage());
        }
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void update(UserDTO userDTO, MultipartFile avatarUrl) {
        User user = userMapper.revertToEntity(userDTO);
//        fileServiceImp.delete(user.getAvatarUrl());  //delete old mainImage file in folder
        user.setAvatarUrl(avatarUrl.getOriginalFilename());
        fileServiceImp.save(avatarUrl);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Error update user " + e.getMessage());
        }
    }

    @Override
    public void hide(Integer id) {
        User user = userRepository.findByIdAndStatus(id,true);
        cartServiceImp.hide(user.getCart().getId());
        user.getOrdersList().forEach(orders -> ordersServiceImp.hide(orders.getId()));
        user.getShipLocationList().forEach(shipLocation -> shipLocationServiceImp.hide(shipLocation.getId()));
        user.setStatus(false);
        userRepository.save(user);
    }

    @Override
    public void show(Integer id) {
        User user = userRepository.findByIdAndStatus(id,false);
        cartServiceImp.show(user.getCart().getId());
        user.getOrdersList().forEach(orders -> ordersServiceImp.show(orders.getId()));
        user.getShipLocationList().forEach(shipLocation -> shipLocationServiceImp.show(shipLocation.getId()));
        user.setStatus(true);
        userRepository.save(user);
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void delete(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
//        fileServiceImp.delete(user.getAvatarUrl());
        cartServiceImp.delete(user.getCart().getId());
        user.getOrdersList().forEach(orders -> ordersServiceImp.delete(orders.getId()));
        user.getShipLocationList().forEach(shipLocation -> shipLocationServiceImp.delete(shipLocation.getId()));
        userRepository.deleteById(id);
    }
}
