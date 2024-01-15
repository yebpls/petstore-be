package com.bc03capstone.bc03cs.service.imp;

import com.bc03capstone.bc03cs.DTO.PetDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PetServiceImp {
    List<PetDTO> getAllByStateAndStatus();
    List<PetDTO> getAllBySpeciesAndStateAndStatus(Integer speciesId);
    PetDTO getByStatusAndId(Integer id);
    void add(String breed, String listPrice, Integer salePercent, String taxIncluded,
        String age, String gender, String color, String weight, String country, String description,
        MultipartFile mainImage, Integer speciesId, MultipartFile[] imageUrlList);
    void updateMainImage(Integer id, MultipartFile mainImage);
    void updateInformation(Integer id, String breed, String listPrice, Integer salePercent, String taxIncluded, String age,
                           String gender, String color, String weight, String country, String description, Integer speciesId);
    void hide(Integer id);
    void show(Integer id);
    void delete(Integer id);
}
