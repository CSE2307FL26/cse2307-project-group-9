package main;

import java.time.LocalDate;

public class Transaction {
    private LocalDate date;
    private String transactionType;
    private double amount;
    private int ID;

    public Transaction(String type, double amount, int ID) {
        this.date = LocalDate.now();
        this.transactionType = type;
        this.amount = amount;
        this.ID = ID;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getType() {
        return transactionType;
    }

    public double getAmount() {
        return  amount;
    }

    public int getID() {
        return ID;
    }
}
