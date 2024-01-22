package com.bc03capstone.bc03cs.repository;

import com.bc03capstone.bc03cs.entity.ShipLocation;
import com.bc03capstone.bc03cs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ShipLocationRepository extends JpaRepository<ShipLocation, Integer>, JpaSpecificationExecutor<ShipLocation> {
    List<ShipLocation> findAllByStatusAndUser(Boolean status, User user);
    ShipLocation findByStatusAndUserAndIsDefault(Boolean status, User user, Boolean isDefault);
    ShipLocation findByStatusAndId(Boolean status, Integer id);

}