package calculator.model;

public class TooLargeNumberException extends RuntimeException {
    public TooLargeNumberException(String message) {
        super(message);
    }
}
