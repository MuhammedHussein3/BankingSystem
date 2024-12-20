package com.bank.accounts;

public class CheckingAccount extends Account {

    public CheckingAccount(Integer accountId, String accountType, String accountHolderName, Double balance) {
        super(accountId, accountType, accountHolderName, balance);
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited " + amount + " to Checking Account. New balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Withdrew " + amount + " from Checking Account. New balance: " + balance);
        } else {
            throw new RuntimeException("Insufficient funds.");
        }
    }


    @Override
    public String toString() {
        return "CheckingAccount{" +
                "accountId=" + accountId +
                ", accountType='" + accountType + '\'' +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
