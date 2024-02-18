package com.bc03capstone.bc03cs.service.imp;


import com.bc03capstone.bc03cs.DTO.PetImageDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface PetImageServiceImp {
    List<PetImageDTO> findAllByPet(Integer petId);
    PetImageDTO findById(Integer id);
    Integer add(Integer petId, MultipartFile imageUrl);
    void hide(Integer id);
    void show(Integer id);
    void delete(Integer id);
}
