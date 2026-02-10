
package src;

/**
 *
 * @author Sibusiso Nahara
 */
public class BankAccount {
    
    private String accountHolder;
    private double balance;
    
    public BankAccount(String accountHolder, double initialDeposit) {
        this.accountHolder = accountHolder;
        this.balance = initialDeposit;
    }
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }
    
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawl successful");
        } else {
            System.out.println("Insufficient funds or invalid amount");
        }
    }
    
    public void displayBalance() {
        System.out.println("Current balance: R" + balance);
    }

    public String getAccountHolder() {
        return accountHolder;
    }
    
}
