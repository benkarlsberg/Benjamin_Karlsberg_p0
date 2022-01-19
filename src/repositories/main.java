package repositories;

import exceptions.NegativeException;
import exceptions.ResourceNotFoundException;
import exceptions.WithdrawException;

public class main {
    public static void main(String[] args) throws NegativeException, WithdrawException, ResourceNotFoundException {
        BankApp app = new BankApp();
        app.run();

    }
}
