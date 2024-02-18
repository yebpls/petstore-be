package com.bc03capstone.bc03cs.service.imp;

import com.bc03capstone.bc03cs.DTO.UserDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UserServiceImp {
    UserDTO findById(Integer id);
    Integer add(String jsonString, MultipartFile avatarUrl);
    Integer update(String jsonString, MultipartFile avatarUrl);
    void hide(Integer id);
    void show(Integer id);
    void delete(Integer id);
}
