package main.BankingApp;


public class Account {
    private int accountNumber;
    private double balance;

    //Constructor
    public Account(int accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    //Return account number
    public int getAccountNumber() {
        return accountNumber;
    }

    //Return current balance
    public double getBalance() {
        return balance;
    }

    //Deposit money
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }

        balance +=amount;
    }

    //Withdraw money
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }

        if (balance < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        balance -= amount;
    }
}
