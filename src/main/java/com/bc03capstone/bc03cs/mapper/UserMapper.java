package com.bc03capstone.bc03cs.mapper;

import com.bc03capstone.bc03cs.DTO.UserDTO;
import com.bc03capstone.bc03cs.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User revertToEntity(UserDTO userDTO) {
        User user = modelMapper.map(userDTO,User.class);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setStatus(true);
        return user;
    }
}
