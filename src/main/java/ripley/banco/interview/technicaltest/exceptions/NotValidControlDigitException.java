package ripley.banco.interview.technicaltest.exceptions;

public class NotValidControlDigitException extends Exception {

    public NotValidControlDigitException() {
        super("Control digit not corresponds to sequence of numbers");
    }

}
