package com.bank.accounts;

public class AccountFactory  {
    public static Account createAccount(String type, AccountRequest request){
       switch (type.toLowerCase()){
           case "savings":
               return new SavingsAccount(null, request.accountType(), request.accountHolderName(), request.balance());
           case "checking":
               return new CheckingAccount(null, request.accountType(), request.accountHolderName(), request.balance());
           default:
               throw new IllegalArgumentException("Invalid account type: " + type);
       }
    }
}
