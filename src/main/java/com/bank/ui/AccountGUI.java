package com.bank.ui;

import com.bank.accounts.Account;
import com.bank.accounts.AccountBuilder;
import com.bank.accounts.AccountRequest;
import com.bank.dao.AccountDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AccountGUI extends JFrame {
    private AccountDAO accountDAO;

    // GUI Components
    private JTextField accountIdField, accountNameField, accountTypeField, accountBalanceField;
    private JTextArea outputArea;
    private JButton createAccountButton, fetchAccountButton, fetchAllAccountsButton;

    public AccountGUI() {
        accountDAO = new AccountDAO();  // Initialize AccountDAO for database interaction

        // Frame setup
        setTitle("Account Management");
        setSize(600, 500);  // Increased size for better visibility
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));  // BorderLayout for better layout management

        // Initialize Components
        initializeComponents();

        // Add Action Listeners
        addListeners();
    }

    // Initialize components
    private void initializeComponents() {
        // Panel for input fields (Create Account)
        JPanel createAccountPanel = new JPanel();
        createAccountPanel.setLayout(new GridLayout(4, 2, 5, 5));
        createAccountPanel.setBackground(new Color(240, 240, 240)); // Light gray background

        createAccountPanel.add(new JLabel("Account Holder Name:"));
        accountNameField = new JTextField(15);
        accountNameField.setBackground(Color.WHITE);
        createAccountPanel.add(accountNameField);

        createAccountPanel.add(new JLabel("Account Type:"));
        accountTypeField = new JTextField(15);
        accountTypeField.setBackground(Color.WHITE);
        createAccountPanel.add(accountTypeField);

        createAccountPanel.add(new JLabel("Balance:"));
        accountBalanceField = new JTextField(15);
        accountBalanceField.setBackground(Color.WHITE);
        createAccountPanel.add(accountBalanceField);

        createAccountButton = new JButton("Create Account");
        createAccountButton.setBackground(new Color(34, 153, 84)); // Green button
        createAccountButton.setForeground(Color.WHITE);
        createAccountPanel.add(createAccountButton);

        // Panel for fetching account by ID
        JPanel fetchAccountPanel = new JPanel();
        fetchAccountPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        fetchAccountPanel.setBackground(new Color(240, 240, 240));

        fetchAccountPanel.add(new JLabel("Account ID:"));
        accountIdField = new JTextField(15);
        accountIdField.setBackground(Color.WHITE);
        fetchAccountPanel.add(accountIdField);

        fetchAccountButton = new JButton("Fetch Account by ID");
        fetchAccountButton.setBackground(new Color(30, 144, 255)); // Blue button
        fetchAccountButton.setForeground(Color.WHITE);
        fetchAccountPanel.add(fetchAccountButton);

        // Panel for fetching all accounts
        JPanel fetchAllAccountsPanel = new JPanel();
        fetchAllAccountsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        fetchAllAccountsPanel.setBackground(new Color(240, 240, 240));

        fetchAllAccountsButton = new JButton("Fetch All Accounts");
        fetchAllAccountsButton.setBackground(new Color(30, 144, 255)); // Blue button
        fetchAllAccountsButton.setForeground(Color.WHITE);
        fetchAllAccountsPanel.add(fetchAllAccountsButton);

        // Output area for displaying results
        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        outputArea.setBackground(new Color(245, 245, 245)); // Light background for output area
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Add components to the main frame
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(createAccountPanel);
        topPanel.add(fetchAccountPanel);
        topPanel.add(fetchAllAccountsPanel);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    // Add action listeners
    private void addListeners() {
        createAccountButton.addActionListener(new CreateAccountListener());
        fetchAccountButton.addActionListener(new FetchAccountByIdListener());
        fetchAllAccountsButton.addActionListener(new FetchAllAccountsListener());
    }

    // Feature 1: Create a new account
    private class CreateAccountListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String accountHolderName = accountNameField.getText();
                String accountType = accountTypeField.getText();
                double balance = Double.parseDouble(accountBalanceField.getText());

                AccountBuilder builder = new AccountBuilder();
                AccountRequest accountRequest = builder
                        .setAccountHolderName(accountHolderName)
                        .setBalance(balance)
                        .setAccountType(accountType)
                        .build();

                accountDAO.saveAccount(accountRequest);
                outputArea.setText("Account created successfully!");
            } catch (Exception ex) {
                outputArea.setText("Error creating account: " + ex.getMessage());
            }
        }
    }

    // Feature 2: Fetch account by ID
    private class FetchAccountByIdListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int accountId = Integer.parseInt(accountIdField.getText());
                Account account = accountDAO.getAccountById(accountId);

                if (account != null) {
                    outputArea.setText("Account Found:\n" + account);
                } else {
                    outputArea.setText("Account not found.");
                }
            } catch (Exception ex) {
                outputArea.setText("Error fetching account: " + ex.getMessage());
            }
        }
    }

    // Feature 3: Fetch all accounts
    private class FetchAllAccountsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                List<Account> accounts = accountDAO.fetchAllAccounts();
                if (accounts.isEmpty()) {
                    outputArea.setText("No accounts found.");
                } else {
                    StringBuilder sb = new StringBuilder("All Accounts:\n");
                    for (Account account : accounts) {
                        sb.append(account).append("\n");
                    }
                    outputArea.setText(sb.toString());
                }
            } catch (Exception ex) {
                outputArea.setText("Error fetching accounts: " + ex.getMessage());
            }
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        AccountGUI gui = new AccountGUI();
        gui.setVisible(true);
    }
}
