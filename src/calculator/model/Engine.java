package calculator.model;

import calculator.Utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Objects;

public class Engine implements CalculatorModel {
    private BigDecimal firstNumber = BigDecimal.ZERO;
    private BigDecimal secondNumber = BigDecimal.ZERO;
    private Operation operation;
    private static final MathContext mathContext = new MathContext(10);

    @Override
    public void setFirstNumber(BigDecimal firstNumber) {
        this.firstNumber = Objects.requireNonNull(firstNumber, "Параметр firstNumber не может быть null!");
    }

    @Override
    public void setSecondNumber(BigDecimal secondNumber) {
        this.secondNumber = Objects.requireNonNull(secondNumber, "Параметр firstNumber не может быть null!");
    }

    @Override
    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    @Override
    public Operation getOperation() {
        return operation;
    }

    @Override
    public BigDecimal getFirstNumber() {
        return firstNumber;
    }

    @Override
    public BigDecimal getSecondNumber() {
        return secondNumber;
    }

    /**
     * @throws ArithmeticException if operation is division
     * and divider is zero.
     * @throws TooLargeNumberException if operation is exponentiation
     * and exponent is greater than  999999999
     * @throws TooLargeNumberException if first or
     * second number is too large
     * @throws UnsupportedOperationException if operation is
     * exponentiation and secondNumber (exponent) isn't whole.
     * @throws UnsupportedOperationException if operation
     * enum-constant isn't supported by this method.
     * @throws IllegalStateException if firstNumber, operation or
     * secondNumber are null.
     * @see Utils#isNumberTooLarge(BigDecimal)
     * */
    @SuppressWarnings("UnnecessaryDefault")
    @Override
    public BigDecimal count() {
        checkData();
        BigDecimal result = switch(operation) {
            case ADD -> firstNumber.add(secondNumber);
            case SUBTRACT -> firstNumber.subtract(secondNumber);
            case MULTIPLE -> firstNumber.multiply(secondNumber);
            case DIVIDE -> divide();
            case EXPONENTIATION -> pow();
            default -> throw new UnsupportedOperationException("Неизвестная операция: " + operation);
        };
        return result.stripTrailingZeros();
    }

    private void checkData() {
        if(operation == null) {
            throw new IllegalStateException("Операция ещё неизвестна!");
        }
        if(firstNumber == null || secondNumber == null) {
            throw new IllegalStateException("Числа ещё неизвестны!");
        }
        if(Utils.isNumberTooLarge(firstNumber) || Utils.isNumberTooLarge(secondNumber)) {
            throw new TooLargeNumberException("Одно из чисел слишком большое!");
        }
    }

    private BigDecimal divide()  {
        if (secondNumber.signum() == 0) {
            throw new ArithmeticException("Нельзя делить на ноль!");
        }
        return firstNumber.divide(secondNumber, 15, RoundingMode.HALF_UP);
    }

    private BigDecimal pow() {
        if(!Utils.isWhole(secondNumber)) {
            throw new UnsupportedOperationException("К сожалению, эта программа не умеет считать степени с нецелыми показателями.");
        }
        if(Utils.isGreaterThen(secondNumber, new BigDecimal("999999999"))) {
            throw new TooLargeNumberException
                    ("Максимальный показатель степени, \nкоторый берёт в расчёт программа: 999999999. \nВы применили показатель больше!");
        }
        return firstNumber.pow(secondNumber.intValue());
    }

    /**
     * @throws ArithmeticException if firstNumber is less than zero
     * */
    @Override
    public BigDecimal countSquareRoot() {
        if(firstNumber.signum() < 0) {
            throw new ArithmeticException("Нельзя вычислить квадратный корень из отрицательного числа!");
        }
        return firstNumber.sqrt(mathContext).stripTrailingZeros();
    }

    @Override
    public String toString() {
        return "firstNumber = " + firstNumber.toPlainString() +
                "; operation = " + operation +
                "; secondNumber = " + secondNumber.toPlainString();
    }
}
