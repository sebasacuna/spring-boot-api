package ripley.banco.interview.technicaltest.utils;

import java.text.DecimalFormat;
import java.util.Arrays;

public class NumbersUtils {

    private NumbersUtils() {
    }

    public static Integer removeDecimals(double number) {
        DecimalFormat decimalFormat = new DecimalFormat("###.#");
        String digit = decimalFormat.format(number);
        return Integer.valueOf(digit);
    }

    public static double[] convertStringToPrimitiveDoubleArray(String number) {
        String[] sequenceNumber = number.split("");
        return Arrays.asList(sequenceNumber).stream().mapToDouble(Double::parseDouble).toArray();
    }

}
