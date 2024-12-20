package com.bank.accounts;

public abstract class Account {
    protected Integer accountId;
    protected String accountType;
    protected String accountHolderName;
    protected Double balance;

    public Account(Integer accountId, String accountType, String accountHolderName, Double balance) {
        this.accountId = accountId;
        this.accountType = accountType;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public Double getBalance() {
        return balance;
    }

    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountType='" + accountType + '\'' +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", balance=" + balance +
                '}';
    }
}

