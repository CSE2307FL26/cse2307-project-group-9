package test;

import org.junit.Test;

import main.BankingApp.Account;
import main.BankingApp.BankService;

import static org.junit.jupiter.api.Assertions.*;


public class CloseBankAccountTest {
    
    @Test 
    public void testCloseExistingAccount() {

        BankService bankService = new BankService();
        Account account = new Account(12345, 500.00);

        bankService.addAccount(account);

        boolean result = bankService.closeAccount(12345);

        assertTrue(result, "The account has closed successfully");
        assertNull(bankService.getAccount(12345), "The account should no longer exist");

    }

}
