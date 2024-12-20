package com.bank.accounts;

public class SavingsAccount extends Account{

    private static final double MIN_BALANCE = 1000.0;

    public SavingsAccount(Integer accountId, String accountType, String accountHolderName, Double balance) {
        super(accountId, accountType, accountHolderName, balance);
    }


    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited " + amount + " to Savings Account. New balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && balance - amount >= MIN_BALANCE) {
            balance -= amount;
            System.out.println("Withdrew " + amount + " from Savings Account. New balance: " + balance);
        } else {
            System.out.println("Insufficient funds or minimum balance not met.");
        }
    }



    @Override
    public String toString() {
        return "SavingsAccount{" +
                "accountId=" + accountId +
                ", accountType='" + accountType + '\'' +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
