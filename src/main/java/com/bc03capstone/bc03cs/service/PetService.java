package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.DTO.PetDTO;
import com.bc03capstone.bc03cs.entity.Pet;
import com.bc03capstone.bc03cs.entity.Species;
import com.bc03capstone.bc03cs.mapper.PetMapper;
import com.bc03capstone.bc03cs.repository.PetRepository;
import com.bc03capstone.bc03cs.service.imp.PetImageServiceImp;
import com.bc03capstone.bc03cs.service.imp.PetServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetService implements PetServiceImp {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private PetImageServiceImp petImageServiceImp;
    @Autowired
    private PetMapper petMapper;

    @Override
    public List<PetDTO> getAllPet() {
        return petRepository.findAll().stream().map(petMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public PetDTO getPetById(Integer id) {
        Optional<Pet> petOptional = petRepository.findById(id);
        if (petOptional.isPresent()) {
            Pet pet = petOptional.get();
            return petMapper.convertToDTO(pet);
        } else {
            return null;
        }
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void addPet(String breed, BigDecimal listPrice, Integer salePercent, BigDecimal taxIncluded,
                       String age, String gender, String color, String weight, String country, String description,
                       Integer speciesId, MultipartFile[] imageUrlList) {
        Pet newPet = new Pet();
        newPet.setBreed(breed);
        newPet.setListPrice(listPrice);
        newPet.setSalePercent(salePercent);
        newPet.setTaxIncluded(taxIncluded);
        newPet.setUploadDate(LocalDate.now());
        newPet.setAge(age);
        newPet.setGender(gender);
        newPet.setColor(color);
        newPet.setWeight(weight);
        newPet.setCountry(country);
        newPet.setDescription(description);
        newPet.setState(true);
        newPet.setStatus(true);
        Species species = new Species();
        species.setId(speciesId);
        newPet.setSpecies(species);
        try {
            petRepository.save(newPet);
        } catch (Exception e) {
            throw new RuntimeException("Error add pet " + e.getMessage());
        }
        for (MultipartFile imageUrl : imageUrlList) {
            petImageServiceImp.addPetImage(imageUrl, newPet.getId());
        }
    }
}