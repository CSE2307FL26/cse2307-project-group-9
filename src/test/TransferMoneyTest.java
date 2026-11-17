package test;

import org.junit.Test;

import main.BankAccount;
import main.Customer;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;


public class TransferMoneyTest {

    @Test
    public void testTransferMoneyBetweenAccounts() {

        Customer bank = new Customer();
        BankAccount account1 = bank.getAccounts().get(0); //ID: 0
        BankAccount account2 = bank.openAccount();        //ID: 1
        account1.deposit(10);
        account2.deposit(10);

        bank.transfer(account1, account2, 5);

        assertEquals(5.00, account1.getBalance(), 0.001);
        assertEquals(15.00, account2.getBalance(), 0.001);
    }

    @Test
    public void testTransferWithInsufficientFunds() {

        Customer bank = new Customer();
        BankAccount account1 = bank.getAccounts().get(0);
        BankAccount account2 = bank.openAccount();
        account1.deposit(100.00);
        account2.deposit(500.00);

        assertThrows(IllegalArgumentException.class, () -> {
            bank.transfer(account1, account2, 200.00);
        });

        assertEquals(100.00, account1.getBalance(), 0.001);
        assertEquals(500.00, account2.getBalance(), 0.001);
    }

    @Test
    public void testNegativeTransfer() {

        Customer bank = new Customer();
        BankAccount account1 = bank.getAccounts().get(0);
        BankAccount account2 = bank.openAccount();
        account1.deposit(1000.00);
        account2.deposit(500.00);

        assertThrows(IllegalArgumentException.class, () -> {
            bank.transfer(account1, account2, -100.00);
        });

        assertEquals(1000.00, account1.getBalance(), 0.001);
        assertEquals(500.00, account2.getBalance(), 0.001);
    }

    @Test
    public void testZeroDollarTransfer() {

        Customer bank = new Customer();
        BankAccount account1 = bank.getAccounts().get(0);
        BankAccount account2 = bank.openAccount();
        account1.deposit(1000.00);
        account2.deposit(500.00);

        assertThrows(IllegalArgumentException.class, () -> {
            bank.transfer(account1, account2, 0.00);
        });

        assertEquals(1000.00, account1.getBalance(), 0.001);
        assertEquals(500.00, account2.getBalance(), 0.001);
    }

    @Test
    public void testTransferToSameAccount() {

        Customer bank = new Customer();
        BankAccount account1 = bank.getAccounts().get(0);
        account1.deposit(1000.00);

        assertThrows(IllegalArgumentException.class, () -> {
            bank.transfer(account1, account1, 200.00);
        });

        assertEquals(1000.00, account1.getBalance(), 0.001);
    }
}
