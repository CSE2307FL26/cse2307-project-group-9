package main;


import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private double balance;
    private List<Transaction> transactions;
    private int ID;
    private boolean active;

    public BankAccount(int accountID) {
        this.balance = 0;
        transactions = new ArrayList<>();
        this.ID = accountID;
        this.active = true;
    }

    public void deposit(double amount) {
        if(amount > 0) {
            this.balance += amount;
            Transaction transaction = new Transaction("Deposit", amount, ID);
            transactions.add(transaction);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public double getBalance() {
        return this.balance;
    }

    public void withdraw(double amount) {
    if (amount < 0) {
        throw new IllegalArgumentException("amount cannot be negative.");
    }
    if (amount > this.balance) {
        throw new IllegalArgumentException("Insufficient funds.");
    }
    this.balance -= amount;
    Transaction transaction = new Transaction("Withdrawal", amount, ID);
    transactions.add(transaction);
}



    public List<Transaction> getTransactionHistory() {
        return transactions;
    }

    public int getAccountId() {
        return ID;
    }

    public boolean getStatus() {
        return active;
    }

    public void setBankStatus(boolean status) {
        active = status;
    }

    public void setBankAmount(double n) {
        balance = n;
    }
 
}
