package com.bc03capstone.bc03cs.repository;

import com.bc03capstone.bc03cs.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {
    List<Pet> findPetEntitiesBySpeciesId(int speciesId);
    List<Pet> findPetEntitiesBySpeciesIdAndBreed (int speciesId, String breed);
    List<Pet> findPetEntitiesByCountry(String country);
    List<Pet> findPetEntitiesByState (Boolean state);
    List<Pet> findPetEntitiesByStatus (Boolean status);
}