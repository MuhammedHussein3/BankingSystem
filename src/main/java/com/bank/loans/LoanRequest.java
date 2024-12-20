package com.bank.loans;

public record LoanRequest(
        int loanId,
        String loanType,
        String borrowerName ,
        double loanAmount ,
        double interestRate,
        int durationMonths
) {
}
