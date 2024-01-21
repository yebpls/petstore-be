package com.bc03capstone.bc03cs.repository;

import com.bc03capstone.bc03cs.entity.Pet;
import com.bc03capstone.bc03cs.entity.PetImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetImageRepository extends JpaRepository<PetImage, Integer> {
    List<PetImage> findAllByPetAndStatus(Pet pet, Boolean status);
    PetImage findByIdAndStatus(Integer id, Boolean status);
}