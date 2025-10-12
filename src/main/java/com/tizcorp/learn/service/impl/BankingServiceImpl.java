package com.tizcorp.learn.service.impl;

import com.tizcorp.learn.dto.Transaction;
import com.tizcorp.learn.dto.User;
import com.tizcorp.learn.repository.TransactionRepository;
import com.tizcorp.learn.repository.UserRepository;
import com.tizcorp.learn.service.BankingService;
import com.tizcorp.learn.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class BankingServiceImpl implements BankingService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private Validation validation;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void register(User user) {
        Optional<User> exitedUser = userRepository.findByUsername(user.getUsername());
        if (exitedUser.isPresent()) {
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
        if (exitedUser.isEmpty()) {
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
        if (exitedUser.isEmpty()) {
            return;
        }
        System.out.println("Số dư tài khoản: " + exitedUser.get().getBalance());
    }

    @Override
    public void deposit(User user) {
        Optional<User> exitedUser = login(user);
        if (exitedUser.isEmpty()) {
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Input amount to be deposit: ");
        int amount = validation.validateNumber(sc.next());
        exitedUser.get().setBalance(exitedUser.get().getBalance() + amount);
        User user1 = userRepository.save(exitedUser.get());
        System.out.println("Deposit successful");
        System.out.println("Số dư hiện tại: " + user1.getBalance());
    }

    @Override
    public void transfer(User user) {
        Optional<User> exitedUser = login(user);
        if (exitedUser.isEmpty()) {
            return;
        }
        System.out.println("Input account amount to be transfer: ");
        Scanner sc = new Scanner(System.in);
        String userName = sc.next();
        Optional<User> targetUser = userRepository.findByUsername(userName);
        if (targetUser.isEmpty()) {
            System.out.println("Account not found");
            return;
        }
        if (targetUser.get().getUsername().equals(user.getUsername())) {
            System.out.println("Cannot transfer user");
            return;
        }
        System.out.println("Input amount to be transfer: ");
        int amount = validation.validateNumber(sc.next());
        if (exitedUser.get().getBalance() < amount) {
            System.out.println("Insufficient balance");
            return;
        }
        performTransfer(exitedUser.get(), targetUser.get(), amount);
    }

    @Override
    public void transactionHistory(User user) {
        Optional<User> exitedUser = login(user);
        if (exitedUser.isEmpty()) {
            return;
        }
        User user1 = exitedUser.get();
        Optional<Transaction> trans = transactionRepository.findByUserId(user1.getId());
        if (trans.isEmpty()) {
           System.out.println("No transaction found");
            return;
        }
        System.out.println("Transaction History successful");
        System.out.println("User: " + exitedUser.get().getUsername());
        System.out.println("Amount: " + trans.get().getAmount());
        System.out.println("Description: " + trans.get().getDescription());
        System.out.println("Created at: " + trans.get().getCreatedAt());
        System.out.println("Target user: " + trans.get().getTargetUserId());
    }

    private void performTransfer(User sender, User receiver, int amount) {
        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);
        userRepository.save(receiver);
        User user2 = userRepository.save(sender);
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setUserId(sender.getId());
        transaction.setTargetUserId(receiver.getId());
        transaction.setType("transfer");
        transactionRepository.save(transaction);
        System.out.println("Transfer successful");
        System.out.println("Số dư hiện tại: " + user2.getBalance());
    }
}
