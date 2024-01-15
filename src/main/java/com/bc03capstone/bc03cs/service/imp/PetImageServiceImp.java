package com.bc03capstone.bc03cs.service.imp;


import com.bc03capstone.bc03cs.DTO.PetImageDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface PetImageServiceImp {
    List<PetImageDTO> getAllByStatusAndPetId(Integer petId);

    PetImageDTO getByStatusAndId(Integer id);

    void add(MultipartFile imageUrl, Integer petId);

    void delete(Integer id);
}
