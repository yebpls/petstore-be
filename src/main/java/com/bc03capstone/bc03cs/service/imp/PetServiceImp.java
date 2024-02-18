package com.bc03capstone.bc03cs.service.imp;

import com.bc03capstone.bc03cs.DTO.PetDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PetServiceImp {
    List<PetDTO> findAll();
    List<PetDTO> findAllBySpecies(Integer speciesId);
    PetDTO findById(Integer id);
    Integer add(String jsonString, MultipartFile mainImage, MultipartFile[] imageUrlList);
    Integer update(String jsonString, MultipartFile mainImage);
    void sold(Integer id);
    void hide(Integer id);
    void show(Integer id);
    void delete(Integer id);
}
