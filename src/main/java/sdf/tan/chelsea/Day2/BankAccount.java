package sdf.tan.chelsea.Day2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class BankAccount {

    private String accountName;
    private String accountNumber;
    private float accountBalance;
    private ArrayList<String> transactions;
    private boolean isClosed;
    private String accountStartDate;
    private String accountEndDate;

    // constructor with initial amount
    public BankAccount(String accountName, float accountBalance) {
        this.accountName = accountName;
        // random generated account number
        Random random = new Random();
        // formula for setting max and min = ((max - min) + 1) + min
        int accountNo = random.nextInt((Integer.MAX_VALUE) - 1 + 1) + 1;
        this.accountNumber = Integer.toString(accountNo);
        this.accountBalance = accountBalance;
        this.transactions = new ArrayList<String>();
        this.isClosed = false;
        // get current date for account start date and format to desired
        LocalDate date = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.accountStartDate = format.format(date);
    }

    // constructor with no amount
    public BankAccount(String accountName) {
        this.accountName = accountName;
        // random generated account number
        Random random = new Random();
        // formula for setting max and min = ((max - min) + 1) + min
        int accountNo = random.nextInt((Integer.MAX_VALUE - 1) + 1) + 1;
        this.accountNumber = Integer.toString(accountNo);
        this.accountBalance = 0;
        this.transactions = new ArrayList<String>();
        this.isClosed = false;
        // get current date for account start date and format to desired
        LocalDate date = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.accountStartDate = format.format(date);
    }
    
    // default constructor
    public BankAccount() {
    }

    // deposit method
    public void deposit (Float amount){
        if (amount < 0){
            // throwing illegal argument exception
            throw new IllegalArgumentException("Invalid input!");
        } else if (isClosed){
            System.out.println("Account is closed!");
        } else {
            accountBalance += amount;
            // adding amount to transactions list
            String transaction = String.format("+$%.2f", amount);
            transactions.add(transaction);
            // acquire current date/time to print a message
            LocalDateTime date = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String currentDate = formatter.format(date);
            System.out.printf("Deposited $%.2f at %s%n", amount, currentDate);
            System.out.printf("Account Balance: $%.2f%n", accountBalance);
        } 
    }

    // withdrawal method
    public void withdraw(Float amount){
        if (amount < 0){
            // error message
            System.err.println("Invalid input!");
        } else if (isClosed){
            System.out.println("Account is closed!");
        } else if (amount > accountBalance) {
            System.out.println("You cannot withdraw more than your balance!");
        } else {
            accountBalance -= amount;
            // adding amount to transactions list
            String transaction = String.format("-$%.2f", amount);
            transactions.add(transaction);
            // acquire current date/time to print a message
            LocalDateTime date = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String currentDate = formatter.format(date);
            System.out.printf("Withdrew $%.2f at %s%n", amount, currentDate);
            System.out.printf("Account Balance: $%.2f%n", accountBalance);
        }
    }

    // close account
    public void closeAccount (){
        isClosed = true;

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        accountEndDate = formatter.format(date);

        System.out.println("Account closed on " + accountEndDate);
        
    }

    // generated getters
    public String getAccountName() {
        return accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public float getAccountBalance() {
        return accountBalance;
    }

    public ArrayList<String> getTransactions() {
        return transactions;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public String getAccountStartDate() {
        return accountStartDate;
    }

    public String getAccountEndDate() {
        return accountEndDate;
    }

    // override toString method
    @Override
    public String toString() {

        return String.format("""
                %s's account:
                Account Number = %s
                Account Balance = $%.2f
                Transactions = %s
                isClosed = %s
                Account Start Date = %s
                Account End Date = %s
                """, accountName, accountNumber, accountBalance, transactions.toString(),String.valueOf(isClosed), accountStartDate, accountEndDate);
    }
    
    
    
    
}
