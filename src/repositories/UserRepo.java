package repositories;

import exceptions.ResourceNotFoundException;
import models.Account;
import models.User;
import util.LinkedList;

public interface UserRepo {

    //CRUD Operations
    public User addUser(User u);
    public User getUser(int id);
    public User getUser(String username);
    public LinkedList<User> getAllUsers();
    public User updateUser(User change);
    public User deleteUser(int id) throws ResourceNotFoundException;

}
