package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.DTO.PetDTO;
import com.bc03capstone.bc03cs.entity.CartItem;
import com.bc03capstone.bc03cs.entity.OrderItem;
import com.bc03capstone.bc03cs.entity.Pet;
import com.bc03capstone.bc03cs.entity.Species;
import com.bc03capstone.bc03cs.mapper.PetMapper;
import com.bc03capstone.bc03cs.repository.CartItemRepository;
import com.bc03capstone.bc03cs.repository.OrderItemRepository;
import com.bc03capstone.bc03cs.repository.PetRepository;
import com.bc03capstone.bc03cs.repository.SpeciesRepository;
import com.bc03capstone.bc03cs.service.imp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    @Autowired
    private CartItemServiceImp cartItemServiceImp;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private OrderItemServiceImp orderItemServiceImp;

    @Autowired
    private OrderItemRepository orderItemRepository;

//    @Cacheable("petList")
    @Override
    public List<PetDTO> findAll() {
        try {
            Thread.sleep(1000); // Delay 1 giây (1000 milliseconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return petRepository.findAllByIsSoldAndStatus(false, true)
                .stream().map(petMapper::convertToDTO).collect(Collectors.toList());
    }

//    @Cacheable("petListBySpecies")
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

//    @CacheEvict(value = {"petList","petListBySpecies"}, allEntries = true)
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public Integer add(String jsonString, MultipartFile mainImage, MultipartFile[] imageUrlList) {
        Pet newPet = petMapper.revertToEntity(jsonString);
        newPet.setUploadDate(LocalDate.now());
        newPet.setMainImage(mainImage.getOriginalFilename());
        fileServiceImp.save(mainImage);
        try {
            petRepository.save(newPet);
            for (MultipartFile imageUrl : imageUrlList) {
                petImageServiceImp.add(newPet.getId(), imageUrl);
            }
            return newPet.getId();
        } catch (Exception e) {
            throw new RuntimeException("Error add pet " + e.getMessage());
        }
    }

//    @CacheEvict(value = {"petList","petListBySpecies"}, allEntries = true)
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public Integer update(String jsonString, MultipartFile mainImage) {
        Pet pet = petMapper.revertToEntity(jsonString);
//        fileServiceImp.delete(pet.getMainImage());  //delete old mainImage file in folder
        pet.setMainImage(mainImage.getOriginalFilename());
        fileServiceImp.save(mainImage);
        try {
            petRepository.save(pet);
            return pet.getId();
        } catch (Exception e) {
            throw new RuntimeException("Error update pet " + e.getMessage());
        }
    }

//    @CacheEvict(value = {"petList","petListBySpecies"}, allEntries = true)
    @Override
    public void sold(Integer id) {
        Pet pet = petRepository.findByIdAndStatus(id,true);
        pet.setIsSold(true);
        petRepository.save(pet);
    }

//    @CacheEvict(value = {"petList","petListBySpecies"}, allEntries = true)
    @Override
    public void hide(Integer id) {
        Pet pet = petRepository.findByIdAndStatus(id,true);
        pet.getPetImageList().forEach(petImage -> petImageServiceImp.hide(petImage.getId()));
        CartItem cartItem = cartItemRepository.findByPet(pet);
        if (cartItem!=null) cartItemServiceImp.hide(cartItem.getId());
        OrderItem orderItem = orderItemRepository.findByPet(pet);
        if (orderItem!=null) orderItemServiceImp.hide(orderItem.getId());
        pet.setStatus(false);
        petRepository.save(pet);
    }

//    @CacheEvict(value = {"petList","petListBySpecies"}, allEntries = true)
    @Override
    public void show(Integer id) {
        Pet pet = petRepository.findByIdAndStatus(id,false);
        pet.getPetImageList().forEach(petImage -> petImageServiceImp.show(petImage.getId()));
        pet.setStatus(true);
        petRepository.save(pet);
    }

//    @CacheEvict(value = {"petList","petListBySpecies"}, allEntries = true)
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    @Override
    public void delete(Integer id) {
        Pet pet = petRepository.findById(id).orElseThrow();
        pet.getPetImageList().forEach(petImage -> petImageServiceImp.delete(petImage.getId()));
        CartItem cartItem = cartItemRepository.findByPet(pet);
        if (cartItem!=null) cartItemServiceImp.delete(cartItem.getId());
        OrderItem orderItem = orderItemRepository.findByPet(pet);
        if (orderItem!=null) orderItemServiceImp.delete(orderItem.getId());
//        fileServiceImp.delete(pet.getMainImage());
        petRepository.deleteById(id);
    }
}