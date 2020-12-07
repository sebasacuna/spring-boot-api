package ripley.banco.interview.technicaltest.exceptions;

public class ForbiddenCheckCharacterException extends Exception {

    public ForbiddenCheckCharacterException() {
        super("Invalid check character, check character must be a numbers or character K");
    }

}
