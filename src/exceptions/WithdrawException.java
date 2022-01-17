package exceptions;

public class WithdrawException extends Exception {

    public WithdrawException(String msg) { super(msg); }

    public WithdrawException() { super("Not enough funds to withdraw that amount"); }

}
