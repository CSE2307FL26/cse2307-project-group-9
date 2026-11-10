package main;

public class BankAccount {

    private double balance;
    private boolean activeAccount;

    public BankAccount() {
        this.balance = 0;
    }

    public void deposit(double amount) {
        if(amount > 0) {
            this.balance += amount;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public double getBalance() {
        return this.balance;
    }


    public double withdraw(double amount) {
        this.balance=this.balance-amount;
        return this.balance;

    }


  
}
