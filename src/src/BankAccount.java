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
    private String pin;
    
    public BankAccount(String accountHolder, double initialDeposit, String pin) {
        this.accountHolder = accountHolder;
        this.balance = initialDeposit;
        this.pin = pin;
    }
    
    public boolean validatePin(String enteredPin) {
        return this.pin.equals(enteredPin);
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
        System.out.printf("Current balance: R%.2f%n", balance);
    }

    public String getAccountHolder() {
        return accountHolder;
    }
    
    public void transfer(BankAccount targetAccount, double amount) {
        
        if (amount > 0 && amount <= balance) {
            this.balance -= amount;
            targetAccount.balance += amount;
            
            transactionHistory.add("Transferred R" + amount + " to " + targetAccount.getAccountHolder());
            targetAccount.transactionHistory.add("Received R" + amount + " from " + this.getAccountHolder());
            
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Invalid transfer amount.");
        }
    }
    
    public String toFileString() {
        return accountHolder + "," + balance + "," + pin;
    }
}




