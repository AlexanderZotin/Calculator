package calculator.model;

import static calculator.Utils.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@NonNull
@Setter
@ToString
public class Engine implements CalculatorModel {
    private BigDecimal firstNumber = BigDecimal.ZERO;
    private BigDecimal secondNumber = BigDecimal.ZERO;
    private Operation operation;
    private static final MathContext mathContext = new MathContext(10);
    
    /**
     * @throws ArithmeticException if operation is division
     * and divider is zero.
     * @throws TooLargeNumberException if operation is exponentiation
     * and exponent is greater than  20
     * @throws TooLargeNumberException if firstNumber is greater then 
     * 1000 and exponent is greater then 5
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
    @Override
    public BigDecimal count() {
        checkData();
        BigDecimal result = switch(operation) {
            case ADD -> firstNumber.add(secondNumber);
            case SUBTRACT -> firstNumber.subtract(secondNumber);
            case MULTIPLE -> firstNumber.multiply(secondNumber);
            case DIVIDE -> divide();
            case EXPONENTIATION -> pow();
            default -> throw new UnsupportedOperationException("Unknown operation: " + operation);
        };
        return result.stripTrailingZeros();
    }

    private void checkData() {
        if(operation == null) {
            throw new IllegalStateException("Operation is now unknown!");
        }
        if(firstNumber == null || secondNumber == null) {
            throw new IllegalStateException("Numbers are now unknown!");
        }
        if(isNumberTooLarge(firstNumber) || isNumberTooLarge(secondNumber)) {
            throw new TooLargeNumberException("Одно из чисел слишко большое!");
        }
    }

    private BigDecimal divide()  {
        if (secondNumber.signum() == 0) {
            throw new ArithmeticException("Нельзя делить на ноль!");
        }
        return firstNumber.divide(secondNumber, 15, RoundingMode.HALF_UP).stripTrailingZeros();
    }

    private BigDecimal pow() {
        checkCorrectBeforePow();
        return firstNumber.pow(secondNumber.intValue());
    }

    private void checkCorrectBeforePow() {
        if(!isWhole(secondNumber)) {
            throw new UnsupportedOperationException("К сожалению, эта программа не умеет считать степени с нецелыми показателями.");
        }
        if(isGreaterThen(secondNumber, new BigDecimal(20))) {
            throw new TooLargeNumberException
                    ("Максимальный показатель степени, \nкоторый берёт в расчёт программа: 20. \nВы применили показатель больше!");
        } else if(isGreaterThen(firstNumber, new BigDecimal(1000))
                && isGreaterThen(secondNumber, new BigDecimal(5))) {
            throw new TooLargeNumberException
                    ("Если число больше 1000, то максимальная степень, \nв которую может возвести число данная программа — 10, вы применили степень больше!");
        }
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
}
