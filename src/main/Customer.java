package main;

import java.util.List;
import java.util.ArrayList;

public class Customer {

    private List<BankAccount> accounts;
    private int size;
    
    public Customer() {
        //First accounts ID starts at 0
        BankAccount userAccount = new BankAccount(0); 
        accounts = new ArrayList<BankAccount>();
        accounts.add(userAccount);
        this.size = 1;
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    //Opens a new BankAccount with the ID 1 more than the previous one. Then adds that
    //account to accounts
    public BankAccount openAccount() {
        BankAccount newAccount = new BankAccount(accounts.size());
        accounts.add(newAccount);
        size++;
        return newAccount;
    }

    public int getSize() {
        return size;
    }

    public boolean closeAccount(int accountNumber) {

        //Find the account
        BankAccount account = getAccounts().get(accountNumber);

        //Check whether the acount exists
        if (account == null) {
            return false;
        }

        //Remove the account from the bank by setting its status as false
        accounts.get(accountNumber).setBankStatus(false);
        accounts.get(accountNumber).setBankAmount(0);
        size--;

        return true;
    }   

    public void transfer(BankAccount sender, BankAccount receiver, double amount) {
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
        //uncomment sender.withdraw(amount) when withdraw is implemented
        sender.withdraw(amount);
        receiver.deposit(amount);
    }
}
