package com.bc03capstone.bc03cs.repository;

import com.bc03capstone.bc03cs.entity.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<PetEntity, Integer> {
}