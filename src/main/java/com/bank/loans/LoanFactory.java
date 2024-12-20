package com.bank.loans;

public class LoanFactory {

    public static Loan createLoan(String loanType, int loanId, String borrowerName, double loanAmount, double interestRate, int durationMonths) {
        switch (loanType) {
            case "Home":
                return new HomeLoan(loanId, LoanType.Home, borrowerName, loanAmount, interestRate, durationMonths);
            case "Car":
                return new CarLoan(loanId, LoanType.Car, borrowerName, loanAmount, interestRate, durationMonths);
            case "Personal":
                return new PersonalLoan(loanId, LoanType.Personal, borrowerName, loanAmount, interestRate, durationMonths);
            default:
                throw new IllegalArgumentException("Invalid loan type: " + loanType);
        }
    }
}

