package models;

import exceptions.NegativeException;
import exceptions.WithdrawException;

public class Account {
    private int accountId;
    private int userId;
    private double balance = 0;
    private String accountType;

    //No-arg constructor
    public Account() {
    }

    // Full-arg constructor
    public Account(int accountId, int userId, String accountType) {
        this.accountId = accountId;
        this.userId = userId;
        this.accountType = accountType;
    }

    //Id-less constructor
    public Account(int userId, String accountType) {
        this.userId = userId;
        this.accountType = accountType;
    }

    public int getAccId() {
        return accountId;
    }

    public void setAccId(int acc_id) {
        this.accountId = acc_id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    /**
     * lowers account balance
     * @param amount amount to withdraw
     * @throws WithdrawException if amount is greater than balance
     * @throws NegativeException if amount is negative
     */
    public void withdraw(double amount) throws WithdrawException, NegativeException {
        if (this.balance > amount) {
            if (amount >= 0.0) {
                setBalance(getBalance() - amount);
            } else {
                throw new NegativeException();
            }
        }
        else {
            throw new WithdrawException();
        }
    }

    /**
     * increases account balance
     * @param amount amount to deposit
     * @throws NegativeException if amount is negative
     */
    public void deposit(double amount) throws NegativeException {
        if (amount >= 0.0) {
            setBalance(getBalance() + amount);
        }
        else {
            throw new NegativeException();
        }
    }

    @Override
    public String toString() {
        return  " Account Id: " + accountId +
                ", User Id: " + userId +
                ", Account Type: '" + accountType + '\'' +
                ", Balance: " + balance + "\n";
    }
}