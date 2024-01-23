package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.DTO.PetDTO;
import com.bc03capstone.bc03cs.DTO.PetImageDTO;
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
    public List<PetDTO> findAll() {
        return petRepository.findAllByIsSoldAndStatus(false, true)
                .stream().map(petMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<PetDTO> findAllBySpecies(Integer speciesId) {
        Species species = speciesRepository.findByIdAndStatus(speciesId,true);
        return petRepository.findAllBySpeciesAndIsSoldAndStatus(species, false,true)
                .stream().map(petMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public PetDTO findById(Integer id) {
        Pet pet = petRepository.findByIdAndStatus(id,true);
        return petMapper.convertToDTO(pet);
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void add(PetDTO petDTO, MultipartFile mainImage, MultipartFile[] imageUrlList) {
        Pet newPet = petMapper.revertToEntity(petDTO);
        newPet.setUploadDate(LocalDate.now());
        newPet.setMainImage(mainImage.getOriginalFilename());
        fileServiceImp.save(mainImage);
        try {
            petRepository.save(newPet);
        } catch (Exception e) {
            throw new RuntimeException("Error add pet " + e.getMessage());
        }
        for (MultipartFile imageUrl : imageUrlList) {
            PetImageDTO petImageDTO = new PetImageDTO();
            petImageDTO.setPetId(newPet.getId());
            petImageServiceImp.add(petImageDTO, imageUrl);
        }
    }

    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void update(PetDTO petDTO, MultipartFile mainImage) {
        Pet pet = petMapper.revertToEntity(petDTO);
//        fileServiceImp.delete(pet.getMainImage());  //delete old mainImage file in folder
        pet.setMainImage(mainImage.getOriginalFilename());
        fileServiceImp.save(mainImage);
        try {
            petRepository.save(pet);
        } catch (Exception e) {
            throw new RuntimeException("Error update pet " + e.getMessage());
        }
    }

    @Override
    public void sold(Integer id) {
        Pet pet = petRepository.findByIdAndStatus(id,true);
        pet.setIsSold(true);
        petRepository.save(pet);
    }

    @Override
    public void hide(Integer id) {
        Pet pet = petRepository.findByIdAndStatus(id,true);
        pet.setStatus(false);
        petRepository.save(pet);
    }

    @Override
    public void show(Integer id) {
        Pet pet = petRepository.findByIdAndStatus(id,false);
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