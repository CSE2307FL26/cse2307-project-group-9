package main;

import java.util.List;
import java.util.ArrayList;

public class Customer {

    private int ID;
    private List<BankAccount> accounts;
    
    public Customer() {
        //First accounts ID starts at 0
        BankAccount userAccount = new BankAccount(0); 
        accounts = new ArrayList<BankAccount>();
        accounts.add(userAccount);
        this.ID = 0;
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    //Opens a new BankAccount with the ID 1 more than the previous one. Then adds that
    //account to accounts
    public BankAccount openAccount() {
        BankAccount newAccount = new BankAccount(accounts.size());
        accounts.add(newAccount);
        return newAccount;
    }

    public int getSize() {
        return accounts.size();
    }
}
