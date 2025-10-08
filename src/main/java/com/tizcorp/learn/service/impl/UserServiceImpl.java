package com.tizcorp.learn.service.impl;

import com.tizcorp.learn.dto.User;
import com.tizcorp.learn.repository.UserRepository;
import com.tizcorp.learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
