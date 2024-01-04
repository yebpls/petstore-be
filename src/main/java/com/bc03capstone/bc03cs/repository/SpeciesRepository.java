package com.bc03capstone.bc03cs.repository;

import com.bc03capstone.bc03cs.entity.SpeciesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeciesRepository extends JpaRepository<SpeciesEntity, Integer> {
}