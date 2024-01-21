package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.DTO.PetDTO;
import com.bc03capstone.bc03cs.entity.Pet;
import com.bc03capstone.bc03cs.entity.Species;
import com.bc03capstone.bc03cs.mapper.PetMapper;
import com.bc03capstone.bc03cs.repository.PetRepository;
import com.bc03capstone.bc03cs.repository.SpeciesRepository;
import com.bc03capstone.bc03cs.service.imp.FileServiceImp;
import com.bc03capstone.bc03cs.service.imp.PetImageServiceImp;
import com.bc03capstone.bc03cs.service.imp.PetServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService implements PetServiceImp {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private PetImageServiceImp petImageServiceImp;
    @Autowired
    private SpeciesRepository speciesRepository;
    @Autowired
    private PetMapper petMapper;
    @Autowired
    private FileServiceImp fileServiceImp;

    @Override
    public List<PetDTO> getAllByStateAndStatus() {
        return petRepository.findAllByStateAndStatus(true, true)
                .stream().map(petMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<PetDTO> getAllBySpeciesAndStateAndStatus(Integer speciesId) {
        Species species = speciesRepository.findByStatusAndId(true, speciesId);
        return petRepository.findAllBySpeciesAndStateAndStatus(species, true, true)
                .stream().map(petMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public PetDTO getByStatusAndId(Integer id) {
        Pet pet = petRepository.findByStatusAndId(true, id);
        return petMapper.convertToDTO(pet);
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void add(String breed, String listPrice, Integer salePercent, String taxIncluded,
                    String age, String gender, String color, String weight, String country, String description,
                    MultipartFile mainImage, Integer speciesId, MultipartFile[] imageUrlList) {
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

        newPet.setMainImage(mainImage.getOriginalFilename());
        fileServiceImp.save(mainImage);

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
            petImageServiceImp.add(imageUrl, newPet.getId());
        }
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void updateMainImage(Integer id, MultipartFile mainImage) {
        Pet pet = petRepository.findByStatusAndId(true, id);
        fileServiceImp.delete(pet.getMainImage());  //delete old mainImage file in folder
        pet.setMainImage(mainImage.getOriginalFilename());
        fileServiceImp.save(mainImage);
        try {
            petRepository.save(pet);
        } catch (Exception e) {
            throw new RuntimeException("Error update MainImage pet " + e.getMessage());
        }
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void updateInformation(Integer id, String breed, String listPrice, Integer salePercent, String taxIncluded, String age,
                                  String gender, String color, String weight, String country, String description, Integer speciesId) {
        Pet pet = petRepository.findByStatusAndId(true, id);
        pet.setBreed(breed);
        pet.setListPrice(listPrice);
        pet.setSalePercent(salePercent);
        pet.setTaxIncluded(taxIncluded);
        pet.setAge(age);
        pet.setGender(gender);
        pet.setColor(color);
        pet.setWeight(weight);
        pet.setCountry(country);
        pet.setDescription(description);
        Species species = new Species();
        species.setId(speciesId);
        pet.setSpecies(species);
        try {
            petRepository.save(pet);
        } catch (Exception e) {
            throw new RuntimeException("Error update information pet " + e.getMessage());
        }
    }

    @Override
    public void hide(Integer id) {
        Pet pet = petRepository.findByStatusAndId(true, id);
        pet.setStatus(false);
        petRepository.save(pet);
    }

    @Override
    public void show(Integer id) {
        Pet pet = petRepository.findByStatusAndId(false, id);
        pet.setStatus(true);
        petRepository.save(pet);
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void delete(Integer id) {
        Pet pet = petRepository.findById(id).orElseThrow();
        pet.getPetImageList().forEach(item -> petImageServiceImp.delete(item.getId()));
//        fileServiceImp.delete(pet.getMainImage());
        petRepository.deleteById(id);
    }
}