package com.tizcorp.learn;

import com.tizcorp.learn.dto.User;
import com.tizcorp.learn.service.BankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.Scanner;

@SpringBootApplication
public class LearnToBankApplication implements CommandLineRunner {
    @Autowired
    BankingService bankingService;

    public static void main(String[] args) {
        SpringApplication.run(LearnToBankApplication.class, args);
    }

    @Override
    public void run(String... args){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("=== BANKING APP ===");
            System.out.println("1. Đăng ký (Register)");
            System.out.println("2. Đăng nhập (Login)");
            System.out.println("3. Xem số dư (Balance)");
            System.out.println("4. Nạp tiền (Deposit)");
            System.out.println("5. Chuyển tiền (transfer)");
            System.out.println("6. Xem lịch sử giao dịch (transaction history)");
            System.out.println("7. Thoát");
            System.out.print("Chọn: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> bankingService.register(inputUser());
                case 2 -> bankingService.login(inputUser());
                case 3 -> bankingService.balance(inputUserName());
                case 4 -> bankingService.deposit(inputUserName());
                case 5 -> bankingService.transfer(inputUserName());
                case 6 -> bankingService.transactionHistory(inputUserName());
                case 7 -> System.exit(0);
                default -> System.out.println("Chọn không hợp lệ!");
            }
        }
    }

    public User inputUserName () {
        Scanner sc = new Scanner(System.in);
        User user = new User();
        System.out.println("Input your Username: ");
        user.setUsername(sc.nextLine());
        System.out.println("Input your Password: ");
        user.setPassword(sc.nextLine());
        return user;
    }

    public User inputUser (){
        Scanner sc = new Scanner(System.in);
        User user = new User();
        System.out.println("Input your Username: ");
        user.setUsername(sc.nextLine());
        System.out.println("Input your password: ");
        user.setPassword(sc.nextLine());
        System.out.println("Input your full name: ");
        user.setFull_name(sc.nextLine());
        user.setBalance(0);
        user.setCreated_at(String.valueOf(new Date()));
        return user;
    }

}
