package main;


import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private double balance;
    private List<Transaction> transactions;
    private int ID;

    public BankAccount(int accountID) {
        this.balance = 0;
        transactions = new ArrayList<>();
        this.ID = accountID;
    }

    public void deposit(double amount) {
        if(amount > 0) {
            this.balance += amount;
            Transaction transaction = new Transaction("Deposit", amount);
            transactions.add(transaction);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public double getBalance() {
        return this.balance;
    }

    public List<Transaction> getTransactionHistory() {
        return transactions;
    }

    public int getAccountId() {
        return ID;
    }
 
}
