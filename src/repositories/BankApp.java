package repositories;

import exceptions.NegativeException;
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
    private AccountRepo ar = new AccountDBRepo();

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

        System.out.println("Welcome to the banking app.");
        System.out.println("Please select an option: ");
        System.out.println("Login - 1\nRegister new user - 2\nExit - 3\n");

        int option = input.nextInt();

        while(option != 0) {

            if (option == 1) {
                user = login();
                option = 0;
            } else if (option == 2) {
                user = createUserAccount();
                setLoggedIn(true);
                option = 0;
            } else if (option == 3) {
                System.out.println("Thank you for using the app. Goodbye!");
                return;
            } else {
                System.out.println("Please enter a valid option.");
                System.out.println("Login - 1\nRegister new user - 2\nExit - 3\n");

            }
        }

        System.out.println("");
        System.out.println("You are now logged in as " + user.getUserName() + "\n");
        System.out.println("Please select an option: ");
        System.out.println("Check account information - 1\nOpen an account - 2\nExit - 3\n");

        option = input.nextInt();

        while(option != 0) {

            if(option == 1) {
                System.out.println("Here are your current accounts: ");
                checkAccounts(user);
                option = 0;
            }
            else if(option == 2) {
               account = createAccount();
               option = 0;
            }
            else if(option ==3) {
                return;
            }
            else {
                System.out.println("Please enter a valid option.");
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
        User user = ur.getUser(username);

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
        int id = user.getUserId();
        LinkedList<Account> accounts = ar.getUserAccounts(id);
        System.out.println(accounts.toString());
    }

    public void makeDeposit(Account account) throws NegativeException {
        Scanner input = new Scanner(System.in);
        System.out.println("How much would you like to deposit?");
        double amount = input.nextDouble();
        account.deposit(amount);
        ar.updateAccount(account);
    }

    public void makeWithdrawal(Account account) {

    }

    public Account createAccount() {

        return null;
    }

}
