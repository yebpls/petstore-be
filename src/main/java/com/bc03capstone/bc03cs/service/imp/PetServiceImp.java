package com.bc03capstone.bc03cs.service.imp;

import com.bc03capstone.bc03cs.DTO.PetDTO;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public interface PetServiceImp {
    List<PetDTO> getAllPet();
    PetDTO getPetById(Integer id);
    void addPet(String breed, BigDecimal listPrice, Integer salePercent, BigDecimal taxIncluded,
                String age, String gender, String color, String weight, String country, String description,
                Integer speciesId, MultipartFile[] imageUrlList);
}
