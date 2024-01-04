package com.bc03capstone.bc03cs.service.imp;

import com.bc03capstone.bc03cs.entity.UserEntity;

public interface LoginServiceImp {
    UserEntity checkLogin(String email, String password);
}
