package com.tizcorp.learn.service.impl;

import com.tizcorp.learn.dto.User;
import com.tizcorp.learn.repository.TransactionRepository;
import com.tizcorp.learn.repository.UserRepository;
import com.tizcorp.learn.service.BankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankingServiceImpl implements BankingService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void register(User user) {
        Optional<User> exitedUser = userRepository.findByUsername(user.getUsername());
        if (exitedUser.isPresent()){
            System.err.println("User already exists");
            return;
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        System.out.println("User registered successfully");
    }

    @Override
    public Optional<User> login(User user) {
        Optional<User> exitedUser = userRepository.findByUsername(user.getUsername());
        if(exitedUser.isEmpty()){
            System.err.println("❌ User not found");
            return Optional.empty();
        }
        if (passwordEncoder.matches(user.getPassword(), exitedUser.get().getPassword())) {
            System.out.println("✅ Login successful");
        } else {
            System.out.println("❌ Invalid password");
        }
        return exitedUser;
    }

    @Override
    public void balance(User user) {
        Optional<User> exitedUser = login(user);
        if (exitedUser.isEmpty()){
            return;
        }
        System.out.println("Số dư tài khoản: " + exitedUser.get().getBalance());
    }

    @Override
    public void deposit() {

    }

    @Override
    public void transfer() {

    }

    @Override
    public void transactionHistory() {

    }
}
