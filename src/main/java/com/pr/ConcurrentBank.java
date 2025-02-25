package com.pr;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentBank {

    private final ConcurrentHashMap<Long, BankAccount> accounts = new ConcurrentHashMap<>();
    private long accountCounter = 1;

    public synchronized BankAccount createAccount(BigDecimal balance) {
        var bankAccount = new BankAccount(accountCounter, balance);
        accounts.put(accountCounter, bankAccount);
        accountCounter++;
        return bankAccount;
    }


    public void transfer(long fromId, long toId, BigDecimal amount) {
        if (fromId == toId) {
            throw new IllegalArgumentException("Нельзя перевести деньги самому себе");
        }

        BankAccount from = accounts.get(fromId);
        BankAccount to = accounts.get(toId);

        if (from == null || to == null) {
            throw new IllegalArgumentException("Один из счетов не найден");
        }

        // Всегда блокируем счета в одном порядке, чтобы избежать Deadlock
        BankAccount firstLock = fromId < toId ? from : to;
        BankAccount secondLock = fromId < toId ? to : from;

        synchronized (firstLock) {
            synchronized (secondLock) {
                // Сохраняем начальные балансы до начала транзакции
                BigDecimal initialFromBalance = from.getBalance();
                BigDecimal initialToBalance = to.getBalance();

                try {
                    from.withdraw(amount);  // Списываем деньги
                    to.deposit(amount);     // Зачисляем деньги

                    // Проверяем, прошла ли транзакция успешно
                    if (from.getBalance().compareTo(initialFromBalance.subtract(amount)) != 0 ||
                            to.getBalance().compareTo(initialToBalance.add(amount)) != 0) {
                        throw new IllegalStateException("Ошибка консистентности данных!");
                    }

                } catch (Exception e) {
                    System.out.println("Ошибка при переводе: " + e.getMessage());

                    // Откат (rollback): возвращаем исходные балансы
                    from.setBalance(initialFromBalance);
                    to.setBalance(initialToBalance);
                }
            }
        }
    }

    public synchronized BigDecimal getTotalBalance() {
        return accounts.values().stream()
                .map(BankAccount::getBalance)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
