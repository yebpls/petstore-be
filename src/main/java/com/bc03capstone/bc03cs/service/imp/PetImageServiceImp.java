package com.bc03capstone.bc03cs.service.imp;


import com.bc03capstone.bc03cs.DTO.PetImageDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface PetImageServiceImp {
    List<PetImageDTO> findAllByPet(Integer petId);
    PetImageDTO findById(Integer id);
    void add(PetImageDTO petImageDTO, MultipartFile imageUrl);
    void delete(Integer id);
}
