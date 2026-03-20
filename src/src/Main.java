package src;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;


/**
 *
 * @author Sibusiso Nahara
 */
public class Main {
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        ArrayList<BankAccount> accounts = new ArrayList<>();
        
        int choice;
        
        do {
            System.out.println("\n===== BANKING SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3.Delete Account");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                choice = 0;
            }
            scanner.nextLine(); // clear buffer
            
            switch (choice) {
                
                case 1:
                    System.out.print("Enter account holder name: ");
                    String name = scanner.nextLine();
                    
                    System.out.print("Enter initial deposit: ");
                    double deposit = scanner.nextDouble();
                    scanner.nextLine();
                    
                    System.out.print("Create a 4-digit PIN: ");
                    String pin = scanner.nextLine();
                    
                    BankAccount newAccount = new BankAccount(name, deposit, pin);
                    accounts.add(newAccount);
                    
                    System.out.println("Account created successfully!");
                    break;
                
                case 2:
                    System.out.print("Enter account holder name: ");
                    String loginName = scanner.nextLine();

                    System.out.print("Enter PIN: ");
                    String enteredPin = scanner.nextLine();

                    boolean found = false;

                    for (BankAccount acc : accounts) {
                        if (acc.getAccountHolder().equals(loginName) && acc.validatePin(enteredPin)) {
                            found = true;
                            System.out.println("Login successful!");

                            // CALL ACCOUNT MENU HERE
                            accountMenu(acc, scanner, accounts);
                        }
                    }

                    if (!found) {
                        System.out.println("Invalid name or PIN.");
                    }
                    break;

                case 3:
                    System.out.print("Enter account name to delete: ");
                    String deleteName = scanner.nextLine();
                    
                    accounts.removeIf(acc -> acc.getAccountHolder().equals(deleteName));
                    System.out.println("Account deleted if it existed.");
                    break;
                    
                case 4:
                    saveAccounts(accounts);
                    System.out.println("Thank you for using the bank system.");
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        } while (choice != 3);

        scanner.close();
        
    }
    
    public static void accountMenu(BankAccount account, Scanner scanner, ArrayList<BankAccount> accounts) {
        
        int option;
        
        do {
            System.out.println("\n===== ACCOUNT MENU =====");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. View Transactions");
            System.out.println("5. Transfer Money");
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");

            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    
                    try {
                        double amount = scanner.nextDouble();
                        account.deposit(amount);
                    } catch (Exception e) {
                        System.out.println("Invalid amount.");
                        scanner.nextLine();
                    }
                    break;

                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    
                    try {
                        double amount = scanner.nextDouble();
                        account.withdraw(amount);
                    } catch (Exception e) {
                        System.out.println("Invalid amount.");
                        scanner.nextLine();
                    }
                    break;

                case 3:
                    account.displayBalance();
                    break;

                case 4:
                    account.showTransactionHistory();
                    break;

                case 5:
                    System.out.print("Enter recipient name: ");
                    scanner.nextLine();
                    String recipientName = scanner.nextLine();
                    
                    System.out.print("Enter amount: ");
                    double transferAmount = scanner.nextDouble();
                    
                    for (BankAccount acc : accounts) {
                        if (acc.getAccountHolder().equals(recipientName)) {
                            account.transfer(acc, transferAmount);
                            return;
                        }
                    }
                    
                    System.out.println("Recipient not found.");
                    break;

                case 6:
                    System.out.println("Logging out...");
                    break;
                    
                default:
                    System.out.println("Invalid option.");
            }

        } while (option != 5);
    }
    
    public static void saveAccounts(ArrayList<BankAccount> accounts) {
        
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("accounts.txt"));
            
            for (BankAccount acc : accounts) {
                writer.println(acc.toFileString());
            }
            
            writer.close();
            System.out.println("Accounts saved successfully.");
            
        } catch (Exception e) {
            System.out.println("Error savings accounts.");
        }
    }
    
    public static ArrayList<BankAccount> loadAccounts() {
        
        ArrayList<BankAccount> accounts = loadAccounts();
        
        try {
            File file = new File("accounts.txt");
            
            if (!file.exists()) {
                return accounts; // no file yet
            }
            
            Scanner fileScanner = new Scanner(file);
            
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(",");
                
                String name = data[0];
                double balance = Double.parseDouble(data[1]);
                String pin = data[2];
                
                BankAccount account = new BankAccount(name, balance, pin);
                accounts.add(account);
            }
            
            fileScanner.close();
            
        } catch (Exception e) {
            System.out.println("Error loading accounts.");
        }
        
        return accounts;
    }
}
            
