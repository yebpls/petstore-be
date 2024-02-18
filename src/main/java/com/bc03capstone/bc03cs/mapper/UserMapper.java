package com.bc03capstone.bc03cs.mapper;

import com.bc03capstone.bc03cs.DTO.UserDTO;
import com.bc03capstone.bc03cs.entity.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public User revertToEntity(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            UserDTO userDTO = objectMapper.readValue(jsonString, UserDTO.class);
            User user = modelMapper.map(userDTO,User.class);
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setStatus(true);
            return user;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error revertToEntity User: " + e.getMessage());
        }
    }
}
