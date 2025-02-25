package com.pr;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) throws Exception {
        ConcurrentBank bank = new ConcurrentBank();

        // Создание счетов
        BankAccount account1 = bank.createAccount(new BigDecimal("1000.00"));
        BankAccount account2 = bank.createAccount(new BigDecimal("500.00"));

        System.out.println(account1.getBalance());
        System.out.println(account2.getBalance());

        // Перевод между счетами
        Thread transferThread1 = new Thread(() -> bank.transfer(
                account1.getAccountNumber(),
                account2.getAccountNumber(),
                new BigDecimal("200.00")));

        Thread transferThread2 = new Thread(() -> bank.transfer(
                account2.getAccountNumber(),
                account1.getAccountNumber(),
                new BigDecimal("100.00")));

        transferThread1.start();
        transferThread2.start();

        try {
            transferThread1.join();
            transferThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(account1.getBalance());
        System.out.println(account2.getBalance());

        // Вывод общего баланса
        System.out.println("Total balance: " + bank.getTotalBalance());
    }
}

