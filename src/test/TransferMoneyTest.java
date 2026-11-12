package test;

import org.junit.Test;


import main.BankingApp.Account;
import main.BankingApp.BankService;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;


public class TransferMoneyTest {
    @Test 
    public void testTransferMoneyBetweenAccounts() {

        Account account1 = new Account(1, 1000.00);
        Account account2 = new Account(2, 500.00);

        BankService bank = new BankService();

        bank.transfer(account1, account2, 200.00);

        assertEquals(800.00, account1.getBalance(), 0.001);
        assertEquals(700.00, account2.getBalance(), 0.001);

    }


    @Test 
    public void testTransferWithInsufficientFunds() {

        Account account1 = new Account(1, 100.00);
        Account account2 = new Account(2, 500.00);

        BankService bank = new BankService();

        assertThrows(IllegalArgumentException.class, () ->  { 
            bank.transfer(account1, account2, 200.00);
    });

        assertEquals(100.00, account1.getBalance(), 0.001);
        assertEquals(500.00, account2.getBalance(), 0.001);
    }

    @Test 
    public void testNegativeTransfer() {
        
        Account account1 = new Account(1, 1000.00);
        Account account2 = new Account(2, 500.00);

        BankService bank = new BankService();

        assertThrows(IllegalArgumentException.class, () -> {
            bank.transfer(account1, account2, -100.00);
        });

        assertEquals(1000.00, account1.getBalance(), 0.001);
        assertEquals(500.00, account2.getBalance(), 0.001);
    
    }

    @Test 
    public void testZeroDollarTransfer() {

        Account account1 = new Account(1, 1000.00);
        Account account2 = new Account(2, 500.00);

        BankService bank = new BankService();

        assertThrows(IllegalArgumentException.class, () -> {
            bank.transfer(account1, account2, 0.00);
        });

        assertEquals(1000.00, account1.getBalance(), 0.001);
        assertEquals(500.00, account2.getBalance(), 0.001);

    }

    @Test 
    public void testTransferToSameAccount() {

        Account account1 = new Account(1, 1000.00);

        BankService bank = new BankService();

        assertThrows(IllegalArgumentException.class, () -> {
            bank.transfer(account1, account1, 200.00);
        });

        assertEquals(1000.00, account1.getBalance(), 0.001);
    }
}

