package exceptions;

public class TooMuchSaleException extends Exception {
    public TooMuchSaleException() {
        super("Too much discount applied to the order!");
    }
}
