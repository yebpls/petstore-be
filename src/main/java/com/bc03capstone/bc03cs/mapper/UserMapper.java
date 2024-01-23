package com.bc03capstone.bc03cs.mapper;

import com.bc03capstone.bc03cs.DTO.UserDTO;
import com.bc03capstone.bc03cs.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    @Autowired
    private ModelMapper modelMapper;
    public UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
