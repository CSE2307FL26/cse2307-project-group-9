package main;
import java.util.HashMap;
import java.util.Map;
//Customer

public class BankService {

    //private Map<Integer, Account> accounts = new HashMap<>();

    public void transfer(Account sender, Account receiver, double amount) {
        //prevent transferring to the same account
        if (sender == receiver) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }

        //prevent negative or zero transfers
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }

        //Check to see if sender has enough money
        if (sender.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        //Complete transfer
        sender.withdraw(amount);
        receiver.deposit(amount);
    }
}
