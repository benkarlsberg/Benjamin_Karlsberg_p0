package repositories;

import exceptions.NegativeException;
import exceptions.ResourceNotFoundException;
import exceptions.WithdrawException;
import models.Account;
import models.User;
import util.LinkedList;

import java.util.Scanner;

public class BankApp {
    private boolean loggedIn;
    Scanner input = new Scanner(System.in);
    private User user;
    private Account account;
    private final UserRepo ur = new UserDBRepo();
    private final AccountRepo ar = new AccountDBRepo();

    public BankApp() {
        this.loggedIn = false;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void run() throws NegativeException, WithdrawException, ResourceNotFoundException {

        System.out.println("Welcome to the banking app.");
        System.out.println("Please select an option: ");
        System.out.println("Login - 1\nRegister new user - 2\nExit - 3\n");

        int option = input.nextInt();

        while(!isLoggedIn()) {

            if (option == 1) {
                user = login();
                if(user == null) {
                    System.out.println("Please select an option: ");
                    System.out.println("Login - 1\nRegister new user - 2\nExit - 3\n");
                    option = input.nextInt();
                }
            } else if (option == 2) {
                user = createUserAccount();
                setLoggedIn(true);
            } else if (option == 3) {
                System.out.println("Thank you for using the app. Goodbye!");
                return;
            } else {
                System.out.println("Please enter a valid option.");
                System.out.println("Login - 1\nRegister new user - 2\nExit - 3\n");
                option = input.nextInt();
            }
        }


        System.out.println();
        System.out.println("Welcome " + user.getFirstName() + "!");

        while(isLoggedIn()) {

            System.out.println("Please select an option:\n");
            System.out.println("Check account information - 1\nMake a withdrawal - 2" +
                    "\nMake a deposit - 3\nOpen an account - 4\nClose an account - 5\nExit - 6\n");
            option = input.nextInt();

            if(option == 1) {

                LinkedList<Account> accounts = getAccounts(user);
                if (!accounts.isEmpty()) {
                    System.out.println("Here are your current accounts: ");
                    System.out.println(accounts);
                } else {
                    System.out.println("You do not have any accounts.");
                }
            }
            else if(option == 2) {
                LinkedList<Account> accounts = getAccounts(user);
                if (!accounts.isEmpty())
                    makeWithdrawal(accounts);
                else {
                    System.out.println("You do not have any accounts.");
                }

            }
            else if (option == 3) {
                LinkedList<Account> accounts = getAccounts(user);
                if (!accounts.isEmpty())
                    makeDeposit(accounts);
                else {
                    System.out.println("You do not have any accounts.");
                }

            }
            else if (option == 4) {
                account = createAccount(user);
            }
            else if (option == 5) {
                LinkedList<Account> accounts = getAccounts(user);
                if (!accounts.isEmpty())
                    closeAccount(accounts);
                else {
                    System.out.println("You do not have any accounts.");
                }
            }
            else if(option == 6) {
                System.out.println("Thank you for using the banking app. Goodbye!");
                return;
            }
            else {
                System.out.println("Please enter a valid option.");
                System.out.println("Check account information - 1\nMake a withdrawal - 2" +
                        "\nMake a deposit - 3\nOpen an account - 4\nExit - 5\n");
            }
        }

    }

    public User login() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your Username: ");
        String username = input.nextLine();
        User user = ur.getUser(username);

        if (ur.getUser(username) == null) {
            System.out.println("Username not found");
            return null;
        }

        System.out.println("Enter your password: ");
        String password = input.nextLine();

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

        return ur.getUser(username);
    }

    public LinkedList<Account> getAccounts(User user) {
        int id = user.getUserId();
        return ar.getUserAccounts(id);
    }

    public void makeDeposit(LinkedList<Account> accounts) throws NegativeException {
        Scanner input = new Scanner(System.in);
        System.out.println("Which account would you like to deposit into? (Enter account ID)\n");
        System.out.println(accounts.toString() + "\n");

        int id = input.nextInt();
        if (idFound(accounts, id)) {
            account = ar.getAccount(id);
        } else {
            System.out.println("You did not enter a valid account ID. Please try again.");
            return;
        }

        System.out.println("Your balance is currently: $" + account.getBalance());
        System.out.println("How much would you like to deposit?");
        double amount = input.nextDouble();
        if(amount < 0) {
            System.out.println("You cannot make a negative deposit.");
        } else {
            account.deposit(amount);
            ar.updateAccount(account);
            System.out.println("You have successfully deposited $" + amount +
                    ". Your account balance is now: $" + account.getBalance());
        }
    }

    public void makeWithdrawal(LinkedList<Account> accounts) throws NegativeException, WithdrawException {
        Scanner input = new Scanner(System.in);
        System.out.println("Which account would you like to withdraw from? (Enter account ID)\n");
        System.out.println(accounts.toString() + "\n");

        int id = input.nextInt();
        if (idFound(accounts, id)) {
            account = ar.getAccount(id);
        } else {
            System.out.println("You did not enter a valid account ID. Please try again.");
            return;
        }

        System.out.println("Your balance is currently: $" + account.getBalance());

        if(account.getBalance() == 0) {
            System.out.println("You do not have enough funds to make a withdrawal.");
        } else {
            System.out.println("How much would you like to withdraw?");
            double amount = input.nextDouble();
            if(amount < 0 || amount > account.getBalance()) {
                System.out.println("You cannot withdraw that amount.");
            } else {
                account.withdraw(amount);
                ar.updateAccount(account);
                System.out.println("You have successfully withdrawn $" + amount + ". " +
                        "Your account balance is now: $" + account.getBalance());
            }
        }
    }

    public Account createAccount(User user) {
        Scanner input = new Scanner(System.in);

        String accountType = null;
        System.out.println("Would you like to open a Checking (1) or Savings (2) account?");
        System.out.println(user.getUserId());

        boolean accountCreated = false;
        int option = input.nextInt();
        while (!accountCreated) {
            if (option == 1) {
                accountType = "checking";
                accountCreated = true;
            } else if (option == 2) {
                accountType = "savings";
                accountCreated = true;
            } else {
                System.out.println("Invalid Input");
                option = input.nextInt();
            }
        }
        Account account = new Account(user.getUserId(), accountType);
        ar.addAccount(account);
        System.out.println("You have successfully opened an account.");

        return account;
    }

    public void closeAccount(LinkedList<Account> accounts) throws ResourceNotFoundException {
        Scanner input = new Scanner(System.in);
        System.out.println("Which account would you like to close? (Enter account ID)\n");
        System.out.println(accounts.toString() + "\n");

        int id = input.nextInt();
        if (idFound(accounts, id)) {
            account = ar.deleteAccount(id);
            System.out.println("You have successfully closed account with ID: " + id);
        } else {
            System.out.println("You did not enter a valid account ID. Please try again.");
        }
    }

    //helper function
    public boolean idFound(LinkedList<Account> accounts, int id) {
        boolean found = false;
        for(int i = 0; i < accounts.getNumItems(); i++) {
            if (id == accounts.find(i).getObject().getAccId()) {
                found = true;
                break;
            }
        }
        return found;
    }

}
