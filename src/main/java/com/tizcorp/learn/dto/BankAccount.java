package com.tizcorp.learn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {
    private int bankAccountNumber;
    private String bankName;
    private double balance;

    private void deposit(double amount){
        this.balance += amount;
    }

    private void withdraw(double amount){
        this.balance -= amount;
    }
}
