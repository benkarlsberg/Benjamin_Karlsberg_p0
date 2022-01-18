package repositories;

import models.Account;
import models.User;
import util.LinkedList;

import java.util.Scanner;

public class BankApp {
    private boolean loggedIn;
    Scanner input = new Scanner(System.in);
    private User user;
    private Account account;
    private UserRepo ur = new UserDBRepo();

    public BankApp() {
        this.loggedIn = false;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void run() {
        int option = input.nextInt();
        while(option != 0) {
            System.out.println("Welcome to the banking app.");
            System.out.println("Please select an option: ");
            System.out.println("Login - 1 \n Create new user account - 2 \n Exit - 3 \n");

            if (option == 1) {
                user = login();
                option = 0;
            } else if (option == 2) {
                user = createUserAccount();
                setLoggedIn(true);
                option = 0;
            } else if (option == 3) {
                return;
            } else {
                System.out.println("Please enter a valid option.");
            }
        }

        option = input.nextInt();
        while(option != 0) {
            System.out.println("You are now logged in.");
            System.out.println("Please select an option: ");
            System.out.println("Check account information - 1 \n Create new account -2 \n Exit - 3");

            if(option == 1) {
                checkAccounts(user);
                option = 0;
            }
            else if(option == 2) {
               account = createAccount();
            }

        }

    }

    public User login() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your Username: ");
        String username = input.nextLine();
        System.out.println("Enter your password: ");
        String password = input.nextLine();

        // login logic
        LinkedList<User> allUsers = ur.getAllUsers();
        User user = allUsers.findByUsername(username);

        while (!isLoggedIn()) {
            if (password.equals(user.getPassword()))
                setLoggedIn(true);
            else {
                System.out.println("Incorrect Password, please try again: ");
                password = input.nextLine();
            }
        }
        return user;
    }

    public User createUserAccount() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your first name: ");
        String firstName = input.nextLine();
        System.out.println("Enter your last name: ");
        String lastName = input.nextLine();
        System.out.println("Enter a username: ");
        String username = input.nextLine();
        System.out.println("Enter a password: ");
        String password = input.nextLine();

        User user = new User(firstName, lastName, username, password);

        ur.addUser(user);

        return user;
    }

    public void checkAccounts(User user) {
        //account.toString();

    }

    public void makeDeposit(Account account) {

    }

    public void makeWithdrawal(Account account) {

    }

    public Account createAccount() {

    }

}
