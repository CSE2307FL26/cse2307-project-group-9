package test;

import main.BankAccount;
import main.Transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

public class BankAccountTest {

    @Test
    public void testDeposit() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(50);
        assertEquals(50, testAccount.getBalance(), 0.01);
    }

    @Test
    public void testInvalidDeposit() {
        BankAccount testAccount = new BankAccount();
        try {
            testAccount.deposit(-50);
            fail();
        } catch (IllegalArgumentException e) {
            //do nothing, test passes
        }
    }

    @Test
    public void testViewTransactionDeposit() {
        BankAccount testAccount = new BankAccount();

        testAccount.deposit(10);
        testAccount.deposit(20);

        List<Transaction> history = testAccount.getTransactionHistory();

        assertEquals(history.size(), 2);
        assertEquals(history.get(0).getAmount(), 10, 0.01);
        assertEquals(history.get(1).getAmount(), 20, 0.01);
    }

    @Test
    public void testViewTransactionDate() {
        BankAccount testAccount = new BankAccount();

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
        BankAccount testAccount = new BankAccount();

        testAccount.deposit(10);
        testAccount.deposit(20);

        List<Transaction> history = testAccount.getTransactionHistory();

        assertEquals(history.size(), 2);
        assertEquals("Deposit", history.get(0).getType());
        assertEquals("Deposit", history.get(1).getType());
    }
}
