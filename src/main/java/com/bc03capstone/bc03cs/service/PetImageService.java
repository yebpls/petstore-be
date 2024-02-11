package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.DTO.PetImageDTO;
import com.bc03capstone.bc03cs.entity.Pet;
import com.bc03capstone.bc03cs.entity.PetImage;
import com.bc03capstone.bc03cs.mapper.PetImageMapper;
import com.bc03capstone.bc03cs.repository.PetImageRepository;
import com.bc03capstone.bc03cs.repository.PetRepository;
import com.bc03capstone.bc03cs.service.imp.FileServiceImp;
import com.bc03capstone.bc03cs.service.imp.PetImageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetImageService implements PetImageServiceImp {
    @Autowired
    private PetImageRepository petImageRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private FileServiceImp fileServiceImp;

    @Autowired
    private PetImageMapper petImageMapper;

    @Cacheable("petImageList")
    @Override
    public List<PetImageDTO> findAllByPet(Integer petId) {
        Pet pet = petRepository.findByIdAndStatus(petId,true);
        return petImageRepository.findAllByPetAndStatus(pet,true)
                .stream().map(petImageMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public PetImageDTO findById(Integer id) {
        PetImage petImage = petImageRepository.findByIdAndStatus(id,true);
        return petImageMapper.convertToDTO(petImage);
    }

    @CacheEvict("petImageList")
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public Integer add(PetImageDTO petImageDTO, MultipartFile imageUrl) {
        PetImage newPetImage = petImageMapper.revertToEntity(petImageDTO);
        fileServiceImp.save(imageUrl);
        newPetImage.setImageUrl(imageUrl.getOriginalFilename());
        try {
            petImageRepository.save(newPetImage);
            return newPetImage.getId();
        } catch (Exception e) {
            throw new RuntimeException("Error add petImage " + e.getMessage());
        }
    }

    @CacheEvict("petImageList")
    @Override
    public void hide(Integer id) {
        PetImage petImage = petImageRepository.findByIdAndStatus(id,true);
        petImage.setStatus(false);
        petImageRepository.save(petImage);
    }

    @CacheEvict("petImageList")
    @Override
    public void show(Integer id) {
        PetImage petImage = petImageRepository.findByIdAndStatus(id,false);
        petImage.setStatus(true);
        petImageRepository.save(petImage);
    }

    @CacheEvict("petImageList")
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void delete(Integer id) {
        PetImage petImage = petImageRepository.findById(id).orElseThrow();
//        fileServiceImp.delete(petImage.getImageUrl());
        petImageRepository.delete(petImage);
    }
}
