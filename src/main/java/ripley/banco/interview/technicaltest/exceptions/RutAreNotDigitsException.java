package ripley.banco.interview.technicaltest.exceptions;

public class RutAreNotDigitsException extends Exception {

    public RutAreNotDigitsException() {
        super("Rut sequence must be only digits");
    }

}