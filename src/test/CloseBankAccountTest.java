package test;

import org.junit.Test;

import main.BankAccount;
import main.MainMenu;
import main.Transaction;
import main.Customer;

import static org.junit.jupiter.api.Assertions.*;


public class CloseBankAccountTest {
    
    @Test 
    public void testCloseExistingAccount() {

        Customer bankService = new Customer(); //opens accountNumber 0

        bankService.openAccount(); //opens accountNumber 1

        boolean result = bankService.closeAccount(1);

        assertTrue(result, "The account has closed successfully");
        assertNull(bankService.getAccounts().get(1), "The account should no longer exist");
    }

}
