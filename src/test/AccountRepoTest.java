package test;

import exceptions.ResourceNotFoundException;
import models.Account;
import models.User;
import repositories.AccountDBRepo;
import repositories.AccountRepo;
import repositories.UserDBRepo;
import repositories.UserRepo;


public class AccountRepoTest {
    public static void main(String[] args){

        UserRepo ur = new UserDBRepo();
        AccountRepo ar = new AccountDBRepo();

        User u1;

        u1 = ur.addUser(new User("Bob", "Smith", "BobSmith", "SmithBob"));

        System.out.println(ur.getUser(u1.getUserId()));

        Account a1;

        a1 = ar.addAccount(new Account(u1.getUserId(), "Checking"));

        System.out.println(ar.getAccount(a1.getAccId()));

        try {
            System.out.println(ar.deleteAccount(a1.getAccId()));
            System.out.println(ur.deleteUser(u1.getUserId()));
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(ar.getAllAccounts());
    }
}
