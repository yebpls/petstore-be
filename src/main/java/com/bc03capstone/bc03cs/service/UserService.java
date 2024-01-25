package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.DTO.PetDTO;
import com.bc03capstone.bc03cs.DTO.UserDTO;
import com.bc03capstone.bc03cs.entity.Pet;
import com.bc03capstone.bc03cs.entity.User;
import com.bc03capstone.bc03cs.mapper.UserMapper;
import com.bc03capstone.bc03cs.repository.UserRepository;
import com.bc03capstone.bc03cs.service.imp.FileServiceImp;
import com.bc03capstone.bc03cs.service.imp.UserServiceImp;
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

    @Override
    public UserDTO findById(Integer id) {
        User user = userRepository.findByIdAndStatus(id,true);
        return userMapper.convertToDTO(user);
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void add(UserDTO userDTO, MultipartFile avatarUrl) {
        User newUser = userMapper.revertToEntity(userDTO);
        newUser.setCreateDate(LocalDate.now());
        newUser.setAvatarUrl(avatarUrl.getOriginalFilename());
        fileServiceImp.save(avatarUrl);
        try {
            userRepository.save(newUser);
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
        user.setStatus(false);
        userRepository.save(user);
    }

    @Override
    public void show(Integer id) {
        User user = userRepository.findByIdAndStatus(id,false);
        user.setStatus(true);
        userRepository.save(user);
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void delete(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
//        fileServiceImp.delete(user.getAvatarUrl());
        userRepository.deleteById(id);
    }
}
