package ripley.banco.interview.technicaltest.utils;

import java.text.DecimalFormat;
import java.util.Arrays;

public class NumbersUtils {

   public static Integer removeDecimals(double number){
       DecimalFormat decimalFormat = new DecimalFormat("###.#");
       String digit = decimalFormat.format(number);
       return new Integer(digit);
   }

   public static double[] convertStringToPrimitiveDoubleArray(String number){
       String[] sequenceNumber = number.split("");
       double[] array = Arrays.asList(sequenceNumber).stream().mapToDouble(Double::parseDouble).toArray();
       return array;
   }

}
