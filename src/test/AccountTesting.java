package test;

import exceptions.NegativeException;
import exceptions.WithdrawException;
import models.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountTesting {

    Account account1;

    @BeforeEach
    public void setUp() {
        account1 = new Account();
        account1.setBalance(200.0);
    }

    @Test
    public void deposit() throws NegativeException {

        account1.deposit(10.0);
        double balance = account1.getBalance();
        assertEquals(210.0, balance);

        boolean thrown = false;
        try {
            account1.deposit(-10.0);
        } catch (NegativeException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void withdraw() throws NegativeException, WithdrawException {
        account1.withdraw(10.0);
        double balance = account1.getBalance();
        assertEquals(190.0, balance);

        boolean thrown = false;
        try {
            account1.withdraw(-10.0);
        } catch (NegativeException e) {
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try {
            account1.withdraw(300.0);
        } catch (WithdrawException e) {
            thrown = true;
        }
        assertTrue(thrown);

    }
}
