package calculator;

import java.math.BigDecimal;
import java.util.Objects;

public final class Utils {
    private Utils() {
        throw new AssertionError("Не должно быть экземпляров класс Utils!");
    }

    public static boolean isPositiveNum(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if((!Character.isDigit(str.charAt(i)))) return false;
        }
        return true;
    }

    public static BigDecimal removeLastDigit(BigDecimal number) {
        Objects.requireNonNull(number);
        String numberAsString = number.toPlainString();
        if(numberAsString.length() == 1) return BigDecimal.ZERO;
        else return new BigDecimal(numberAsString.substring(0, numberAsString.length() - 1));
    }

    public static String removeLastDisplayCharacter(String displayText) {
         if (displayText == null || displayText.isEmpty() || displayText.length() == 1) return "0";
         return displayText.substring(0, displayText.length() - 1);
    }

    public static boolean isWhole(BigDecimal number) {
        return number.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0;
    }

    public static boolean isGreaterThen(BigDecimal number, BigDecimal then) {
        return number.compareTo(then) > 0;
    }

    public static boolean isNumberTooLarge(BigDecimal number) {
        return isGreaterThen(number, new BigDecimal("999999999999999999999999999999999999999999999999999"));
    }
}
