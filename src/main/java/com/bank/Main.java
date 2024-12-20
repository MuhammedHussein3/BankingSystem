package com.bank;

import com.bank.accounts.AccountBuilder;
import com.bank.accounts.AccountRequest;
import com.bank.accounts.AccountType;
import com.bank.dao.AccountDAO;
import com.bank.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        try (Connection connection = DatabaseConnection.getConnection()){
            if (connection != null){
                System.out.println("success connect to mysql");
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Fail to connect to mysql");
        }




    }
}