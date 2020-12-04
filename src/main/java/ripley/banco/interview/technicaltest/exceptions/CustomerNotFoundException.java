package ripley.banco.interview.technicaltest.exceptions;

public class CustomerNotFoundException extends Exception {

    public CustomerNotFoundException() {
        super("Customer not exist");
    }

}
