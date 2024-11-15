package calculator.model;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import lombok.AllArgsConstructor;

public interface CalculatorModel {
    void setFirstNumber(BigDecimal number);
    void setSecondNumber(BigDecimal secondNumber);
    void setOperation(Operation operation);
    Operation getOperation();
    BigDecimal getFirstNumber();
    BigDecimal getSecondNumber();

    @AllArgsConstructor
    enum Operation {
        ADD('+'),
        SUBTRACT('-'),
        MULTIPLE('*'),
        DIVIDE('/'),
        EXPONENTIATION('^');

        private final char designation;

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
