package ripley.banco.interview.technicaltest.services;

import ripley.banco.interview.technicaltest.dtos.Customer;
import ripley.banco.interview.technicaltest.exceptions.CustomerNotFoundException;
import ripley.banco.interview.technicaltest.exceptions.ForbiddenControlCharacterException;
import ripley.banco.interview.technicaltest.exceptions.NotValidControlDigitException;
import ripley.banco.interview.technicaltest.exceptions.RutAreNotDigitsException;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomerMockData();

    Customer findCustomer(String rut) throws CustomerNotFoundException, NotValidControlDigitException,
            ForbiddenControlCharacterException, RutAreNotDigitsException;
}
