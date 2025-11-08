package com.tiz.learntobank.service.impl;

import com.tiz.learntobank.dto.UserDTO;
import com.tiz.learntobank.enity.User;
import com.tiz.learntobank.exception.CommonException;
import com.tiz.learntobank.mapper.UserMapper;
import com.tiz.learntobank.repository.UserRepository;
import com.tiz.learntobank.service.UserService;
import com.tiz.learntobank.untils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void register(UserDTO user) {
        Optional<User> exitedUser = userRepository.findByUsername(user.getUsername());
        if (exitedUser.isPresent()) {
            throw new CommonException(Constants.CommonException.AUTHENTICATION_ERROR);
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        User newUser = userMapper.toEntity(user);
        userRepository.save(newUser);
    }

    @Override
    public void login(UserDTO user) {
        Optional<User> exitedUser = userRepository.findByUsername(user.getUsername());
        if (exitedUser.isEmpty()) {
            throw new CommonException(Constants.CommonException.AUTHENTICATION_ERROR);
        }
        if (passwordEncoder.matches(user.getPassword(), exitedUser.get().getPassword())) {
            System.out.println("✅ Login successful");
        } else {
            System.out.println("❌ Invalid password");
        }
    }
}
