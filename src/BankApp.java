import java.util.Scanner;

public class BankApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the banking app.");
        System.out.println("Please select an option: ");
        System.out.println("Login - 1 \n Create new user account - 2 \n");
        int option = input.nextInt();

        if (option == 1) {
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
