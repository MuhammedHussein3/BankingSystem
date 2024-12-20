package com.bank.dao;

import com.bank.accounts.*;
import com.bank.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {


    // Method to get an account by its accountId
    public Account getAccountById(Integer accountId) {
        String query = "SELECT * FROM accounts WHERE account_id = ?";
        try (Connection connection = DatabaseConnection.getConnection()
             ) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, accountId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String accountType = resultSet.getString("account_type");
                String accountHolderName = resultSet.getString("account_holder_name");
                double balance = resultSet.getDouble("balance");

                AccountBuilder builder = new AccountBuilder();
                AccountRequest accountRequest = builder
                        .setAccountHolderName(accountHolderName)
                        .setBalance(balance)
                        .build();
                return AccountFactory.createAccount(accountType, accountRequest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Account not found");
        }
        return null;
    }

    // Method to save an account to the database
    public void saveAccount(AccountRequest account) {
        String query = "INSERT INTO accounts (account_holder_name, account_type, balance) VALUES (?, ?, ?)";

        // Using try-with-resources to manage the connection and statement automatically
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set the parameters for the PreparedStatement
            statement.setString(1, account.accountHolderName());
            statement.setString(2, account.accountType());
            statement.setDouble(3, account.balance());

            // Execute the update
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Account saved successfully.");
            } else {
                System.out.println("Failed to save account.");
            }

        } catch (SQLException e) {
            // Log and print stack trace for debugging
            e.printStackTrace();
            System.out.println("Error while saving account.");
        }
    }


    // Method to update account balance
    public void updateBalance(AccountUpdate account) {
        String query = "UPDATE accounts SET balance = ? WHERE account_id = ?";
        try (Connection connection = DatabaseConnection.getConnection()
             ) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDouble(1, account.balance());
            statement.setInt(2, account.accountId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to fetch all accounts
    public List<Account> fetchAllAccounts(){
        List<Account> accounts = new ArrayList<>();
        String query = "SELECT * FROM accounts";
        try (Connection connection = DatabaseConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int accountId = resultSet.getInt("account_id");
                String accountType = resultSet.getString("account_type");
                String accountHolderName = resultSet.getString("account_holder_name");
                double balance = resultSet.getDouble("balance");

                AccountBuilder builder = new AccountBuilder();
                AccountRequest accountRequest = builder
                        .setAccountHolderName(accountHolderName)
                        .setBalance(balance)
                        .build();

                accounts.add(AccountFactory.createAccount(accountType, accountRequest));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return accounts;
    }


}
