package com.bc03capstone.bc03cs.repository;

import com.bc03capstone.bc03cs.entity.PetImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetImageRepository extends JpaRepository<PetImageEntity, Integer> {
}