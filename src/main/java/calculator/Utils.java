package calculator;

import java.math.BigDecimal;

import lombok.NonNull;

public final class Utils {
    private Utils() {
        throw new AssertionError("No instances of class Utils for you!");
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

    public static BigDecimal removeLastDigit(@NonNull BigDecimal number) {
        String numberAsString = number.toPlainString();
        if(numberAsString.length() == 1) return BigDecimal.ZERO;
        else return new BigDecimal(numberAsString.substring(0, numberAsString.length() - 1));
    }

    public static String removeLastDisplayCharacter(String displayText) {
         if (displayText == null || displayText.isEmpty() || displayText.length() == 1) return "0";
         return displayText.substring(0, displayText.length() - 1);
    }

    public static boolean isWhole(@NonNull BigDecimal number) {
        return number.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0;
    }

    public static boolean isGreaterThen(@NonNull BigDecimal number, @NonNull BigDecimal then) {
        return number.compareTo(then) > 0;
    }

    public static boolean isNumberTooLarge(BigDecimal number) {
        return isGreaterThen(number, new BigDecimal("999999999999999999999"));
    }
}
