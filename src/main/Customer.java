package main;

import java.util.List;
import java.util.ArrayList;

public class Customer {

    private int ID;
    private List<BankAccount> accounts;
    
    public Customer() {
        BankAccount userAccount = new BankAccount(0);
        accounts = new ArrayList<BankAccount>();
        accounts.add(userAccount);
        this.ID = 0;
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public BankAccount openAccount() {
        int prevID = accounts.get(accounts.size()-1).getAccountId();
        BankAccount newAccount = new BankAccount(prevID + 1);
        accounts.add(newAccount);
        return newAccount;
    }
}
