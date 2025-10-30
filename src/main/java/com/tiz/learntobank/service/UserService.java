package com.tiz.learntobank.service;

import com.tiz.learntobank.dto.UserDTO;

public interface UserService {
    void register(UserDTO user);
    void login(UserDTO user);
}
