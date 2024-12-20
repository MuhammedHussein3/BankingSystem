package com.bank.accounts;

public record AccountRequest(
         String accountType, String accountHolderName, Double balance
) {
}
