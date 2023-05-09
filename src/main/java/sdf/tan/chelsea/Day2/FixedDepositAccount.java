package sdf.tan.chelsea.Day2;

public class FixedDepositAccount extends BankAccount{

    private float interest;
    private int duration;



    public FixedDepositAccount(String name, Float accountBalance, int duration) {
        super(name, accountBalance);
        this.duration = duration;
        if(this.duration == 3){
            this.interest = 3;
        } else if (this.duration == 6){
            this.interest = 6;
        }
    }

    

    public FixedDepositAccount(String accountName, float accountBalance, float interest, int duration) {
        super(accountName, accountBalance);
        this.interest = interest;
        this.duration = duration;
    }



    public FixedDepositAccount(String accountName, float accountBalance) {
        super(accountName, accountBalance);
        this.interest = 3;
        this.duration = 3;
    }

    @Override
    public float getAccountBalance() {
        float accountBalance = super.getAccountBalance();
        return accountBalance + (accountBalance * (interest / 100));
    }

    @Override
    public void deposit(Float amount) {
        System.out.println("You cannot deposit into a fixed deposit account!");
    }

    @Override
    public void withdraw(Float amount) {
       System.out.println("You cannot withdraw from a fixed deposit account!");
    }

    @Override
    public String toString() {
        return String.format("""
                %s's Fixed Deposit Account:
                Account Balance = $%.2f
                Duration = %d months
                """, getAccountName(), getAccountBalance(), duration);
    }
    

    public void setDuration(int duration) {
        if (!((duration == 3) || (duration == 6))){
            System.out.println("Invalid duration! Duration can only be 3 or 6");
        } else if (duration == 3){
            duration = 3;
            interest = 3;
            System.out.println("Duration changed to 3 months");
        } else if (duration == 6){
            duration = 6;
            interest = 6;
            System.out.println("Duration changed to 6 months");
        }
    
    }
    
}
