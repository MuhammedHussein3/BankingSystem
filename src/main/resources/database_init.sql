create schema banking_system;

use banking_system;

CREATE TABLE accounts (
    account_id INT PRIMARY KEY,
    account_holder_name VARCHAR(255) NOT NULL,
    account_type ENUM('Savings', 'Checking') NOT NULL,
    balance DECIMAL(15, 2) NOT NULL
);

CREATE TABLE loans(
loan_id INT PRIMARY KEY AUTO_INCREMENT,
loan_type ENUM('Home', 'Car', 'Personal') NOT NULL,
borrower_name VARCHAR(255) NOT NULL,
loan_amount DECIMAL(15, 2) NOT NULL,
interest_rate DECIMAL(5, 2) NOT NULL
);
