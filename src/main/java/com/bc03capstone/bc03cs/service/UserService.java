package com.bc03capstone.bc03cs.service;

import com.bc03capstone.bc03cs.repository.UserRepository;
import com.bc03capstone.bc03cs.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceImp {
    @Autowired
    private UserRepository userRepository;
}
