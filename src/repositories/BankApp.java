package repositories;

import models.User;

import java.util.Scanner;

public class BankApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the banking app.");
        System.out.println("Please select an option: ");
        System.out.println("Login - 1 \n Create new user account - 2 \n Exit - 3 \n");
        int option = input.nextInt();
        boolean loggedIn = false;

        while(option != 3) {
            if (option == 1) {
                login(input);
                loggedIn = true;
                option = 3;
            } else if (option == 2) {
                User newUser = createAccount();
                loggedIn = true;
                option = 3;
            } else {
                System.out.println("Please enter a valid option.");
            }
        }


    }

    // return Id
    public static void login(Scanner input) {
        System.out.println("Please enter your Username: ");
        String username = input.nextLine();
        System.out.println("Please enter your password: ");
        String password = input.nextLine();
    }

    public static User createAccount() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your first name: ");
        String firstName = input.nextLine();
        System.out.println("Enter your last name: ");
        String lastName = input.nextLine();
        System.out.println("Enter a username: ");
        String username = input.nextLine();
        System.out.println("Enter a password: ");
        String password = input.nextLine();

        return new User(firstName, lastName, username, password);
    }

}
