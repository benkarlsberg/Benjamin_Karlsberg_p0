package repositories;

import exceptions.ResourceNotFoundException;
import models.Account;
import util.LinkedList;

public interface AccountRepo {

    //CRUD Operations
    public Account addAccount(Account a);
    public Account getAccount(int id);
    public LinkedList<Account> getAllAccounts();
    public Account updateAccount(Account change);
    public Account deleteAccount(int id) throws ResourceNotFoundException;
    public LinkedList<Account> getUserAccounts(int id);

}
