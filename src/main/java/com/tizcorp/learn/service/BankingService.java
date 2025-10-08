package com.tizcorp.learn.service;

import com.tizcorp.learn.dto.User;

import java.util.Optional;

public interface BankingService {
    void register(User user);
    Optional<User> login(User user);
    void balance(User user);
    void deposit();
    void transfer();
    void transactionHistory();
}
