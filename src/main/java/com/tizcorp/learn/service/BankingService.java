package com.tizcorp.learn.service;

import com.tizcorp.learn.dto.User;

public interface BankingService {
    void register(User user);
    void login(User user);
    void balance(User user);
    void deposit();
    void transfer();
    void transactionHistory();
}
