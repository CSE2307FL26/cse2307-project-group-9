package main;

import java.util.List;
import java.util.Scanner;


public class MainMenu {

    private static final int EXIT_SELECTION = 2;
	private static final int MAX_SELECTION = 5;

	private BankAccount currentAccount;
    private Customer customer;
    private Scanner keyboardInput;

    public MainMenu() {
        this.customer = new Customer();
        this.keyboardInput = new Scanner(System.in);
        this.currentAccount = customer.getAccounts().get(0);
    }

    public void displayOptions() {
        System.out.println("Welcome to the 237 Bank App!");
        
        System.out.println("1. Make a deposit");
        System.out.println("2. Exit the app");
        System.out.println("3. See Transaction History");
        System.out.println("4. Make an additional bank account");
        System.out.println("5. Switch Bank Accounts");

    }

    public int getUserSelection(int max) {
        int selection = -1;
        while(selection < 1 || selection > max) {
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
            case 3: 
                showTransactionHistory();
                break;
            case 4:
                makeAdditionalBankAccount();
                break;
            case 5:
                switchBankAccounts();
                break;
        }
    }

    public void performDeposit() {
        double depositAmount = -1;
        while(depositAmount < 0) {
            System.out.print("How much would you like to deposit: ");
            depositAmount = keyboardInput.nextDouble();
        }
        currentAccount.deposit(depositAmount);
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

    public void makeAdditionalBankAccount() {
        customer.openAccount();
    }

    public void run() {
        int selection = -1;
        while(selection != EXIT_SELECTION) {
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
