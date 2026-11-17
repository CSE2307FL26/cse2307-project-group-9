package main;

import java.time.LocalDate;

public class Transaction {
    private LocalDate date;
    private String transactionType;
    private double amount;

    public Transaction(String type, double amount) {
        this.date = LocalDate.now();
        this.transactionType = type;
        this.amount = amount;
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
}
