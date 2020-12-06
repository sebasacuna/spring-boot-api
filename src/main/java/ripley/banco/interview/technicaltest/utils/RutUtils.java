package ripley.banco.interview.technicaltest.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ripley.banco.interview.technicaltest.exceptions.ForbiddenControlCharacterException;
import ripley.banco.interview.technicaltest.exceptions.NotValidControlDigitException;
import ripley.banco.interview.technicaltest.exceptions.RutAreNotDigitsException;

import java.util.LinkedList;

import static ripley.banco.interview.technicaltest.utils.NumbersUtils.removeDecimals;

public class RutUtils {

    private static final Logger logger = LoggerFactory.getLogger(RutUtils.class);

    private static final int[] checkFactors = {2, 3, 4, 5, 6, 7};

    private RutUtils() {
    }

    public static boolean isValidRut(String rut) throws NotValidControlDigitException,
            ForbiddenControlCharacterException, RutAreNotDigitsException {

        if (!checkSequenceNumberHaveOnlyDigits(rut)) {
            logger.error("Sequence number must contain only digits");
            throw new RutAreNotDigitsException();
        }
        if (!checkValidControlDigit(rut)) {
            logger.error("Control digit must contain only digits or k");
            throw new ForbiddenControlCharacterException();
        }
        if (!verifyControlDigitModule11(rut)) {
            logger.error("Character control does not correspond to sequence of numbers");
            throw new NotValidControlDigitException();
        }

        return false;
    }

    private static boolean checkSequenceNumberHaveOnlyDigits(String rut) {
        return StringUtils.chop(rut).chars().allMatch(Character::isDigit);
    }

    private static boolean checkValidControlDigit(String rut) {

        LinkedList<String> sequenceRut = ArrayConverts.stringToLinkedList(rut);
        String controlDigit = sequenceRut.getLast();
        if (controlDigit.equals("K")) {
            return true;
        }
        if (controlDigit.chars().allMatch(Character::isDigit)) {
            return true;
        }
        return false;
    }

    private static boolean verifyControlDigitModule11(final String rut) {
        LinkedList<String> sequenceRut = ArrayConverts.stringToLinkedList(rut);
        String controlDigit = sequenceRut.getLast();
        double[] rutSequence = NumbersUtils.convertStringToPrimitiveDoubleArray(StringUtils.chop(rut));
        String verifyControlDigit = getControlDigit(rutSequence);

        return verifyControlDigit.equals(controlDigit);
    }

    /**
     * Module 11 algorithm: get control digit from an sequence of numbers
     *
     * @param rutSequence
     * @return
     */
    public static String getControlDigit(final double[] rutSequence) {
        double[] product = new double[rutSequence.length];
        double sumOfProducts;

        sumOfProducts = getSumOfProducts(rutSequence, product);

        double elevenModule = sumOfProducts % 11;
        double controlDigit = 11 - elevenModule;

        int controlDigitWithOutDecimals = removeDecimals(controlDigit);
        String finalControlDigit = checkConversionsForDigit(controlDigitWithOutDecimals);

        return finalControlDigit;
    }

    private static double getSumOfProducts(final double[] rutSequence, final double[] product) {

        int productIndex = 0;
        int sequenceIndex = 0;
        double sumOfProducts = 0;
        // Loop inverted sequence
        for (int index = rutSequence.length - 1; index >= 0; index--) {
            //Step 1: product between check sequence and rut sequence
            product[productIndex] = checkFactors[sequenceIndex] * rutSequence[index];
            //Step 2: accumulation sum of products
            sumOfProducts = sumOfProducts + product[productIndex];
            sequenceIndex++;
            productIndex++;
            // If the sequence reach the final of checkFactors the cycle repeats
            if (productIndex % checkFactors.length == 0 && productIndex != 0) {
                sequenceIndex = 0;
            }
        }
        return sumOfProducts;
    }

    private static String checkConversionsForDigit(final int controlDigitWithOutDecimals) {
        switch (controlDigitWithOutDecimals) {
            case 11:
                return "10";
            case 10:
                return "K";
            default:
                return String.valueOf(controlDigitWithOutDecimals);
        }
    }

}
