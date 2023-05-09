package sdf.tan.chelsea.Day2;

public class App {

    public static void main(String[] args) {
        BankAccount myAccount = new BankAccount("Chelsea");
        BankAccount account2 = new BankAccount("Peko", 1000);

        System.out.println(myAccount.toString());
        System.out.println(account2.toString());
        // checking the thrown Illegal Argument Exception 
        // myAccount.deposit(-1.0f);

        // casting to float
        myAccount.deposit(100.00f);
        myAccount.deposit(50f);
        System.out.println(myAccount.getTransactions());

        // check withdraw methods
        myAccount.withdraw(-1f);
        myAccount.withdraw(200f);
        myAccount.withdraw(30f);
        System.out.println(myAccount.getTransactions());

        FixedDepositAccount myFixedDeposit = new FixedDepositAccount("Chelsea", 200.0f, 3);
        System.out.println(myFixedDeposit.toString());

    }
    
}
