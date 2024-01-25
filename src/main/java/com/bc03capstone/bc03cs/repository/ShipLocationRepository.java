package com.bc03capstone.bc03cs.repository;

import com.bc03capstone.bc03cs.entity.ShipLocation;
import com.bc03capstone.bc03cs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShipLocationRepository extends JpaRepository<ShipLocation, Integer>, JpaSpecificationExecutor<ShipLocation> {
    List<ShipLocation> findAllByUserAndStatus(User user, Boolean status);
    ShipLocation findByUserAndIsDefaultAndStatus(User user, Boolean isDefault, Boolean status);
    ShipLocation findByIdAndStatus(Integer id, Boolean status);

}