package com.bank.dao;

import com.bank.loans.Loan;
import com.bank.loans.LoanBuilder;
import com.bank.loans.LoanFactory;
import com.bank.loans.LoanRequest;
import com.bank.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanDAO {

    // Method to save a loan
    public void saveLoan(Loan loan) {
        String query = "INSERT INTO loans (loan_type, borrower_name, loan_amount, interest_rate, duration_months) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, loan.getLoanType().name());
            statement.setString(2, loan.getBorrowName());
            statement.setDouble(3, loan.getLoanAmount());
            statement.setDouble(4, loan.getInterestRate());
            statement.setInt(5, loan.getDurationMonths());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to fetch all loans
    public List<Loan> fetchAllLoans() {
        List<Loan> loans = new ArrayList<>();
        String query = "SELECT * FROM loans";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int loanId = resultSet.getInt("loan_id");
                String loanType = resultSet.getString("loan_type");
                String borrowerName = resultSet.getString("borrower_name");
                double loanAmount = resultSet.getDouble("loan_amount");
                double interestRate = resultSet.getDouble("interest_rate");
                int durationMonths = resultSet.getInt("duration_months");

                LoanBuilder builder = new LoanBuilder();
                LoanRequest loanRequest = builder
                        .setLoanId(loanId)
                        .setLoanAmount(loanAmount)
                        .setLoanType(loanType)
                        .setBorrowName(borrowerName)
                        .setDurationMonths(durationMonths)
                        .setInterestRate(interestRate)
                        .build();

                loans.add(LoanFactory.createLoan(loanType, loanId, borrowerName, loanAmount, interestRate, durationMonths));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loans;
    }
}

