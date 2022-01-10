import java.util.Scanner;

public class BankApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the banking app.");
        System.out.println("Are you a returning user? y/n");
        String returning = input.next();

        if (returning.equals("y")) {
            System.out.println("Please enter your Username: ");
            String username = input.nextLine();
            System.out.println("Please enter your password: ");
            String password = input.nextLine();
        }
        else {
            System.out.println("");
        }

    }
}
