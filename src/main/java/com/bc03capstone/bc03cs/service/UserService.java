package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.DTO.UserDTO;
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
}
