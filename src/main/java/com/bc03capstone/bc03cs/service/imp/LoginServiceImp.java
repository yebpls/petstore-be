package com.bc03capstone.bc03cs.service.imp;

import com.bc03capstone.bc03cs.entity.User;

public interface LoginServiceImp {
    User checkLogin(String email, String password);
}
