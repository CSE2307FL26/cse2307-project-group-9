package main;

import java.util.List;
import java.util.Scanner;

public class MainMenu {

    private static final int EXIT_SELECTION = 2;
	private static final int MAX_SELECTION = 7;

    private BankAccount currentAccount;
    private Customer customer;
    private Scanner keyboardInput;

    public MainMenu() {

        this.customer = new Customer();
        this.keyboardInput = new Scanner(System.in);
        this.currentAccount = customer.getAccounts().get(0);
    }

    public void displayOptions() {

        System.out.println("\nWelcome to the 237 Bank App!");

        System.out.println("1. Make a deposit");
        System.out.println("2. Exit the app");
        System.out.println("3. See Transaction History");
<<<<<<< HEAD
        System.out.println("4. Make a withdrawal");
        System.out.println("5. Check current balance")
        System.out.println("6. Make an additional bank account");
        System.out.println("7. Switch Bank Accounts");
=======

        System.out.println("4. Make a withdrawal");
        System.out.println("5. Make an additional bank account");
        System.out.println("6. Switch Bank Accounts");
>>>>>>> 69dc53da1df97acba94ffbf8b08640fcf31fcaa7

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
            case 4: 
                showTransactionHistory();
                break;
<<<<<<< HEAD
            case 4: 
                performWithdraw();
                break;
            
            case 5: 
                currentBalance();
                break;
            
            case 6:
                makeAdditionalBankAccount();
                break;
            case 7:
=======

            case 4:
                performWithdraw();
                break;
            case 5:
                makeAdditionalBankAccount();
                break;
            case 6:
>>>>>>> 69dc53da1df97acba94ffbf8b08640fcf31fcaa7
                switchBankAccounts();
                break;
            case 6:
                performTransfer();
                break;
            case 3:
                performCloseAccount();
                break;
            case 2:
                System.out.println("Thank you for using 237 Bank!");
                break;
        }
    }

    // Deposit functionality
    public void performDeposit() {

        double depositAmount = -1;

        while (depositAmount <= 0) {

            System.out.print("How much would you like to deposit: ");
            depositAmount = keyboardInput.nextDouble();
        }
        currentAccount.deposit(depositAmount);
        System.out.println("Deposit successful!");
        System.out.println("New balance: $" + currentAccount.getBalance());
    }

    public void showTransactionHistory() {
        List<Transaction> history = currentAccount.getTransactionHistory();

        if (history.size() == 0) {
            System.out.println("No transaction history");
        }


        for (Transaction t : history) {
            String symbol = "";
            if (t.getType().equals("Deposit")) {
                symbol = "+$";
            } else if (t.getType().equals("Withdrawal")) {
                symbol = "-$";
            }
            System.out.println(t.getDate() + " | " + t.getType() + " | " + symbol + t.getAmount());
        }
    }

     public void currentBalance() {

        double balance = currentAccount.getBalance();
        System.out.print("Your current balance is " + balance);

    }


    public void makeAdditionalBankAccount() {
        customer.openAccount();
        System.out.println("Made a new account with ID " + customer.getAccounts().get(customer.getSize()-1).getAccountId());
    }

    public void switchBankAccounts() {
        System.out.println("You are currently in bank account " + currentAccount.getAccountId());
        System.out.println("Which bank account would you like to switch to?");
        for (BankAccount a : customer.getAccounts()) {
            System.out.print("Account: " + a.getAccountId() + "   ");
        } 
        System.out.println();
        int selection = -1;
        
        while (selection < 0 || selection >= customer.getAccounts().size()) {
            System.out.print("Enter account ID: ");
            selection = keyboardInput.nextInt();
        } 
        currentAccount = customer.getAccounts().get(selection);
        System.out.println("Switched to account " + currentAccount.getAccountId());

    }

    // Transfer functionality
    public void performTransfer() {
        int selection = -1;
        
        while (selection < 0 || selection >= customer.getAccounts().size()) {
            System.out.print("Enter account ID for account you want to transfer money to: ");
            selection = keyboardInput.nextInt();
        } 
        BankAccount receiver = customer.getAccounts().get(selection);

        if (currentAccount == null) {
            System.out.println("Recipient account does not exist.");
            return;
        }

        System.out.print("Enter the amount to transfer: ");

        double amount = keyboardInput.nextDouble();

        try {

            // Call the transfer method in BankService
            customer.transfer(currentAccount, receiver, amount);

            System.out.println("Transfer successful!");

            System.out.println("Your new balance: $"
                    + currentAccount.getBalance());

            System.out.println("Recipient's new balance: $"
                    + receiver.getBalance());

        } catch (IllegalArgumentException e) {

            System.out.println("Transfer failed: " + e.getMessage());
        }
    }

    // Close account functionality
    public void performCloseAccount() {

        if (currentAccount == null) {
            System.out.println("Your account is already closed.");
            return;
        }

        System.out.print(
            "Are you sure you want to close your account? (yes/no): "
        );

        String confirmation = keyboardInput.next();

        if (confirmation.equalsIgnoreCase("yes")) {

            boolean result = customer.closeAccount(
                    currentAccount.getAccountId()
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




    public void performWithdraw() {
    double withdrawAmount = -1;
    while (withdrawAmount < 0) {
        System.out.print("How much would you like to withdraw? ");
        withdrawAmount = keyboardInput.nextDouble();
    }

    try {
        currentAccount.withdraw(withdrawAmount);
        System.out.println("Withdrawal successful.");
    } catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
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



