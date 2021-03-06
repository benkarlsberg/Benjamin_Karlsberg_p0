package test;

import exceptions.ResourceNotFoundException;
import models.User;
import repositories.UserDBRepo;
import repositories.UserRepo;

import java.io.IOException;

public class UserRepoTest {
    public static void main(String[] args) throws IOException, ResourceNotFoundException {

        UserRepo ur = new UserDBRepo();

        User u1;
        User u2;

        u1 = ur.addUser(new User("Bob", "Smith", "BobSmith", "SmithBob"));
        u2 = ur.addUser(new User("Jane", "Doe", "JaneDoe", "DoeJane"));



        System.out.println(ur.getUser(u1.getUserId()));
        System.out.println(u1.getUserId());
        System.out.println(ur.getAllUsers());

        try {
            System.out.println(ur.deleteUser(u1.getUserId()));
            System.out.println(ur.deleteUser(u2.getUserId()));
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(ur.getAllUsers());
    }
}
