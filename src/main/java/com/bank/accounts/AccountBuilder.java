package com.bank.accounts;

public class AccountBuilder {
    String accountType;
    private String accountHolderName;
    private Double balance;

    public AccountBuilder setAccountType(String accountType) {
        this.accountType = accountType;
        return this;
    }

    public AccountBuilder setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
        return this;
    }

    public AccountBuilder setBalance(Double balance) {
        this.balance = balance;
        return this;
    }

    public AccountRequest build(){
        return new AccountRequest(accountType, accountHolderName, balance);
    }
}
