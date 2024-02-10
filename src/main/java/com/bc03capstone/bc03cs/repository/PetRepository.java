package com.bc03capstone.bc03cs.repository;

import com.bc03capstone.bc03cs.entity.Pet;
import com.bc03capstone.bc03cs.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> , JpaSpecificationExecutor<Pet> {
    List<Pet>  findAllByIsSoldAndStatus(Boolean isSold, Boolean status);
    List<Pet> findAllBySpeciesAndIsSoldAndStatus(Species species, Boolean isSold, Boolean status);
    Pet findByIdAndStatus(Integer id, Boolean status);
}