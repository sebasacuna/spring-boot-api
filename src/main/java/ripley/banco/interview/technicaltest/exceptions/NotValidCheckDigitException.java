package ripley.banco.interview.technicaltest.exceptions;

public class NotValidCheckDigitException extends Exception {

    public NotValidCheckDigitException() {
        super("Check digit does not corresponds to the sequence of numbers");
    }

}
