public class Account {
    private double balance = 0;
    private String accountType;

//    public Account() {
//
//    }

    public double getBalance() {
        return this.balance;
    }

    public void withdraw(double amount) {
        if (this.balance > amount) {
            if (amount >= 0.0) {
                this.balance -= amount;
                System.out.println("You have successfully withdrawn $" + amount + ". " +
                        "Your account balance is now: $" + this.balance + "");
            } else {
                System.out.println("Please enter a valid amount");
            }
        }
        else {
            System.out.println("Sorry, your account balance is too low for that withdrawal amount.");
        }
    }

    public void deposit(double amount) {
        if (amount >= 0.0) {
            this.balance += amount;
            System.out.println("You have made a successful deposit of $" + amount + ". " +
                    "Your account balance is now: $" + this.balance + "");
        }
        else {
            System.out.println("Please enter a valid amount");
        }
    }
}