package com.bank.loans;

public class LoanBuilder {

    private Integer loanId;
    private String loanType;
    private String borrowName;
    private double loanAmount;
    private double interestRate;
    private int durationMonths;

    public LoanBuilder setLoanId(Integer loanId) {
        this.loanId = loanId;
        return this;
    }

    public LoanBuilder setLoanType(String loanType) {
        this.loanType = loanType;
        return this;
    }

    public LoanBuilder setBorrowName(String borrowName) {
        this.borrowName = borrowName;
        return this;
    }

    public LoanBuilder setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
        return this;
    }

    public LoanBuilder setInterestRate(double interestRate) {
        this.interestRate = interestRate;
        return this;
    }

    public LoanBuilder setDurationMonths(int durationMonths) {
        this.durationMonths = durationMonths;
        return this;
    }

    public LoanRequest build(){
        return new LoanRequest(this.loanId,
                this.loanType,
                this.borrowName,
                this.loanAmount,
                this.interestRate,
                this.durationMonths);
    }
}
