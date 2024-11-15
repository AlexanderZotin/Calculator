package calculator.model;

import lombok.NonNull;

public class TooLargeNumberException extends RuntimeException {
    public TooLargeNumberException(@NonNull String message) {
        super(message);
    }
}
