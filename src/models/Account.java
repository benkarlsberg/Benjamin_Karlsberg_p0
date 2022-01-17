package models;

import exceptions.NegativeException;
import exceptions.WithdrawException;

public class Account extends User {
    private double balance = 0;
    private String accountType;

    public Account(String firstName, String lastName, String userName, String password) {
        super(firstName, lastName, userName, password);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void withdraw(double amount) throws WithdrawException, NegativeException {
        if (this.balance > amount) {
            if (amount >= 0.0) {
                setBalance(getBalance() - amount);
                System.out.println("You have successfully withdrawn $" + amount + ". " +
                        "Your account balance is now: $" + this.balance + "");
            } else {
                throw new NegativeException();
            }
        }
        else {
            throw new WithdrawException();
        }
    }

    public void deposit(double amount) throws NegativeException {
        if (amount >= 0.0) {
            setBalance(getBalance() + amount);
            System.out.println("You have made a successful deposit of $" + amount + ". " +
                    "Your account balance is now: $" + this.balance + "");
        }
        else {
            throw new NegativeException();
        }
    }
}