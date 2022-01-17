package exceptions;

public class NegativeException extends Exception {

    public NegativeException(String msg) { super(msg); }

    public NegativeException() { super("Cannot enter a negative amount"); }
}
