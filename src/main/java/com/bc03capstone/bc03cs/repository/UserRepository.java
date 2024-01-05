package com.bc03capstone.bc03cs.repository;

import com.bc03capstone.bc03cs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
}
