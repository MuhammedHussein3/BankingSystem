package com.bank.loans;

public class HomeLoan extends Loan{


    public HomeLoan(Integer loanId, LoanType loanType, String borrowName, double loanAmount, double interestRate, int durationMonths) {
        super(loanId, loanType, borrowName, loanAmount, interestRate, durationMonths);
    }

    @Override
    public double calculateMonthlyPayment() {
        double monthlyRate = getInterestRate() / 100 / 12;
        return (getLoanAmount() * monthlyRate) / (1- Math.pow(1 + monthlyRate, -getDurationMonths()));
    }

    @Override
    public LoanType getLoanType() {
        return LoanType.Home;
    }
}
