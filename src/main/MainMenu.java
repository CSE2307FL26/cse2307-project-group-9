
package main;

import java.util.Scanner;
import main.BankingApp.Account;
import main.BankingApp.BankService;

public class MainMenu {

    private static final int EXIT_SELECTION = 4;
    private static final int MAX_SELECTION = 4;

    private BankService bankService;
    private Account userAccount;
    private Scanner keyboardInput;

    public MainMenu() {

        this.bankService = new BankService();

        // Create the customer's account
        this.userAccount = new Account(12345, 500.00);

        // Create a second account for transfer testing
        Account recipientAccount = new Account(67890, 250.00);

        // Register both accounts with the bank
        bankService.addAccount(userAccount);
        bankService.addAccount(recipientAccount);

        this.keyboardInput = new Scanner(System.in);
    }

    public void displayOptions() {

        System.out.println("\nWelcome to the 237 Bank App!");

        System.out.println("1. Make a deposit");
        System.out.println("2. Transfer money");
        System.out.println("3. Close account");
        System.out.println("4. Exit the app");
    }

    public int getUserSelection(int max) {

        int selection = -1;

        while (selection < 1 || selection > max) {

            System.out.print("Please make a selection: ");

            selection = keyboardInput.nextInt();
        }

        return selection;
    }

    public void processInput(int selection) {

        switch (selection) {

            case 1:
                performDeposit();
                break;

            case 2:
                performTransfer();
                break;

            case 3:
                performCloseAccount();
                break;

            case 4:
                System.out.println("Thank you for using 237 Bank!");
                break;
        }
    }

    // Deposit functionality
    public void performDeposit() {

        if (bankService.getAccount(userAccount.getAccountNumber()) == null) {
            System.out.println("Your account is closed.");
            return;
        }

        double depositAmount = -1;

        while (depositAmount <= 0) {

            System.out.print("How much would you like to deposit: ");

            depositAmount = keyboardInput.nextDouble();
        }

        userAccount.deposit(depositAmount);

        System.out.println("Deposit successful!");
        System.out.println("New balance: $" + userAccount.getBalance());
    }

    // Transfer functionality
    public void performTransfer() {

        // Check whether the customer's account is still open
        if (bankService.getAccount(userAccount.getAccountNumber()) == null) {
            System.out.println("Your account is closed.");
            return;
        }

        System.out.print("Enter the recipient's account number: ");

        int recipientNumber = keyboardInput.nextInt();

        // Find the recipient's account
        Account receiver = bankService.getAccount(recipientNumber);

        if (receiver == null) {
            System.out.println("Recipient account does not exist.");
            return;
        }

        System.out.print("Enter the amount to transfer: ");

        double amount = keyboardInput.nextDouble();

        try {

            // Call the transfer method in BankService
            bankService.transfer(userAccount, receiver, amount);

            System.out.println("Transfer successful!");

            System.out.println("Your new balance: $"
                    + userAccount.getBalance());

            System.out.println("Recipient's new balance: $"
                    + receiver.getBalance());

        } catch (IllegalArgumentException e) {

            System.out.println("Transfer failed: " + e.getMessage());
        }
    }

    // Close account functionality
    public void performCloseAccount() {

        if (bankService.getAccount(userAccount.getAccountNumber()) == null) {
            System.out.println("Your account is already closed.");
            return;
        }

        System.out.print(
            "Are you sure you want to close your account? (yes/no): "
        );

        String confirmation = keyboardInput.next();

        if (confirmation.equalsIgnoreCase("yes")) {

            boolean result = bankService.closeAccount(
                    userAccount.getAccountNumber()
            );

            if (result) {

                System.out.println(
                    "Your account has been successfully closed!"
                );

            } else {

                System.out.println("Unable to close account.");
            }

        } else {

            System.out.println("Account closure cancelled.");
        }
    }

    public void run() {

        int selection = -1;

        while (selection != EXIT_SELECTION) {

            displayOptions();

            selection = getUserSelection(MAX_SELECTION);

            processInput(selection);
        }
    }

    public static void main(String[] args) {

        MainMenu bankApp = new MainMenu();

        bankApp.run();
    }
}

