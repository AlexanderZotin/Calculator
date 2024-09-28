package calculator.model;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

public interface CalculatorModel {
    void setFirstNumber(BigDecimal number);
    void setSecondNumber(BigDecimal secondNumber);
    void setOperation(Operation operation);
    Operation getOperation();
    BigDecimal getFirstNumber();
    BigDecimal getSecondNumber();

    enum Operation {
        ADD('+'),
        SUBTRACT('-'),
        MULTIPLE('*'),
        DIVIDE('/'),
        EXPONENTIATION('^');

        private final char designation;

        Operation(char designation) {
            this.designation = designation;
        }

        public static Operation getOperationByDesignation(char designation) {
            for(Operation currentCandidate : Operation.values()) {
                if(designation == currentCandidate.designation) {
                    return currentCandidate;
                }
            }
            throw new NoSuchElementException(String.valueOf(designation));
        }

        @Override
        public String toString() {
            return String.valueOf(designation);
        }
    }

    BigDecimal count();
    BigDecimal countSquareRoot() throws ArithmeticException;
}
