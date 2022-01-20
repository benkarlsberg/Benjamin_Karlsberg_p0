package test;

import exceptions.NegativeException;
import exceptions.ResourceNotFoundException;
import models.Account;
import models.User;
import org.junit.jupiter.api.*;
import repositories.AccountDBRepo;
import repositories.AccountRepo;
import repositories.UserDBRepo;
import repositories.UserRepo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DBTesting {
    static UserRepo ur;
    static AccountRepo ar;
    static User u1;
    static Account a1;
    static Account a2;

    @BeforeAll
    public static void before() {

        ur = new UserDBRepo();
        ar = new AccountDBRepo();
        u1 = ur.addUser(new User("Eric", "Smith", "EricSmith", "SmithEric"));
        a1 = ar.addAccount(new Account(u1.getUserId(), "Checking"));
        a2 = ar.addAccount(new Account(u1.getUserId(), "Savings"));
    }

    @Test
    @Order(1)
    public void getUser() {
        User user1 = ur.getUser(u1.getUserId());
        assertEquals(u1.toString(), user1.toString());

        user1 = ur.getUser(u1.getUserName());
        assertEquals(u1.toString(), user1.toString());

    }

    @Test
    @Order(2)
    public void getAccount() {
        Account acc1 = ar.getAccount(a1.getAccId());
        assertEquals(a1.toString(), acc1.toString());
    }

    @Test
    @Order(3)
    public void updateAccount() throws NegativeException {

        a1.deposit(10);
        ar.updateAccount(a1);
        assertEquals(10.0, (ar.getAccount(a1.getAccId())).getBalance());
    }

    @Test
    @Order(4)
    public void getUserAccounts() {

        int accounts = ar.getUserAccounts(u1.getUserId()).getNumItems();
        assertEquals(2, accounts);
    }

    @Test
    @Order(5)
    public void deleteAccount() throws ResourceNotFoundException {

        ar.deleteAccount(a1.getAccId());
        boolean thrown = false;
        try {
            ar.deleteAccount(a1.getAccId());
        } catch (ResourceNotFoundException e) {
            thrown = true;
        }
        assertTrue(thrown);

        ar.deleteAccount(a2.getAccId());
        thrown = false;
        try {
            ar.deleteAccount(a2.getAccId());
        } catch (ResourceNotFoundException e) {
            thrown = true;
        }
        assertTrue(thrown);

        int accounts = ar.getUserAccounts(u1.getUserId()).getNumItems();
        assertEquals(0, accounts);
    }

    @Test
    public void deleteUser() throws ResourceNotFoundException {

        ur.deleteUser(u1.getUserId());
        boolean thrown = false;
        try {
            ur.deleteUser(u1.getUserId());
        } catch (ResourceNotFoundException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
}
