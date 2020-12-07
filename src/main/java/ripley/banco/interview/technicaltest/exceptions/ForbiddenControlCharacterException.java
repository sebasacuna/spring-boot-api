package ripley.banco.interview.technicaltest.exceptions;

public class ForbiddenControlCharacterException extends Exception {

    public ForbiddenControlCharacterException() {
        super("Invalid control character, control character must be a numbers or character K");
    }

}
