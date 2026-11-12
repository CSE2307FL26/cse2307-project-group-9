package test;

import main.BankAccount;
import main.MainMenu;
import main.Transaction;
import main.Customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

public class BankAccountTest {

    @Test
    public void testDeposit() {
        BankAccount testAccount = new BankAccount(0);
        testAccount.deposit(50);
        assertEquals(50, testAccount.getBalance(), 0.01);
    }

    @Test
    public void testInvalidDeposit() {
        BankAccount testAccount = new BankAccount(0);
        try {
            testAccount.deposit(-50);
            fail();
        } catch (IllegalArgumentException e) {
            //do nothing, test passes
        }
    }

    @Test
    public void testViewTransactionDeposit() {
        BankAccount testAccount = new BankAccount(0);

        testAccount.deposit(10);
        testAccount.deposit(20);

        List<Transaction> history = testAccount.getTransactionHistory();

        assertEquals(history.size(), 2);
        assertEquals(history.get(0).getAmount(), 10, 0.01);
        assertEquals(history.get(1).getAmount(), 20, 0.01);
    }

    @Test
    public void testViewTransactionDate() {
        BankAccount testAccount = new BankAccount(0);

        testAccount.deposit(10);
        testAccount.deposit(20);

        List<Transaction> history = testAccount.getTransactionHistory();

        LocalDate currentDate = LocalDate.now(); 

        assertEquals(history.size(), 2);
        assertEquals(currentDate, history.get(0).getDate());
        assertEquals(currentDate, history.get(1).getDate());
    }

    @Test 
    public void testViewTransactionType() {
        BankAccount testAccount = new BankAccount(0);

        testAccount.deposit(10);
        testAccount.deposit(20);

        List<Transaction> history = testAccount.getTransactionHistory();

        assertEquals(history.size(), 2);
        assertEquals("Deposit", history.get(0).getType());
        assertEquals("Deposit", history.get(1).getType());
    }

    @Test 
    public void testAdditionalAccountCreation() {
        Customer customer = new Customer(); //starts with one bank account
        assertEquals(1, customer.getAccounts().size());

        customer.openAccount();

        assertEquals(2, customer.getAccounts().size());
    }

    @Test
    public void testAdditionalAccountID() {
        Customer customer = new Customer(); //starts with account ID 0
        customer.openAccount();
        customer.openAccount();

        //IDs are assigned in order of creation
        assertEquals(0, customer.getAccounts().get(0).getAccountId());
        assertEquals(1, customer.getAccounts().get(1).getAccountId());
        assertEquals(2, customer.getAccounts().get(2).getAccountId());
    }
}
