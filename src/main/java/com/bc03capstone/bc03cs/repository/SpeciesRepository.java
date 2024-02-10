package com.bc03capstone.bc03cs.repository;

import com.bc03capstone.bc03cs.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Integer> {
    List<Species> findAllByStatus(Boolean status);

    Species findByIdAndStatus(Integer id, Boolean status);
}