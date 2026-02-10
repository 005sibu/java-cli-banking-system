package src;

import java.util.ArrayList;


/**
 *
 * @author Sibusiso Nahara
 */
public class BankAccount {
    
    private String accountHolder;
    private double balance;
    private ArrayList<String> transactionHistory = new ArrayList<>();
    
    public BankAccount(String accountHolder, double initialDeposit) {
        this.accountHolder = accountHolder;
        this.balance = initialDeposit;
    }
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: R" + amount);
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }
    
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: R" + amount);
            System.out.println("Withdrawl successful");
        } else {
            System.out.println("Insufficient funds or invalid amount");
        }
    }
    
    public void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            System.out.println("Transaction History:");
            for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
        }
    }
    
    public void displayBalance() {
        System.out.println("Current balance: R" + balance);
    }

    public String getAccountHolder() {
        return accountHolder;
    }
    
}
