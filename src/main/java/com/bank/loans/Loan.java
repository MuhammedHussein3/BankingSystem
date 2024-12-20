package com.bank.loans;

public abstract class Loan {

    private Integer loanId;
    private LoanType loanType;
    private String borrowName;
    private double loanAmount;
    private double interestRate;
    private int durationMonths;

    public Loan(Integer loanId,
                LoanType loanType,
                String borrowName,
                double loanAmount,
                double interestRate,
                int durationMonths) {
        this.loanId = loanId;
        this.loanType = loanType;
        this.borrowName = borrowName;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.durationMonths = durationMonths;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public String getBorrowName() {
        return borrowName;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public int getDurationMonths() {
        return durationMonths;
    }

    public abstract double calculateMonthlyPayment();

    public abstract LoanType getLoanType();
}
