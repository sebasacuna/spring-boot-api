package ripley.banco.interview.technicaltest.exceptions;

public class ControlCharacterContainLetterException extends Exception {

    public ControlCharacterContainLetterException() {
        super("Invalid control character, control character must be a numbers or character K");
    }

}
