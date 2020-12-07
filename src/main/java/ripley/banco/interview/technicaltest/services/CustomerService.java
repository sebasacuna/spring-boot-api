package ripley.banco.interview.technicaltest.services;

import ripley.banco.interview.technicaltest.dtos.Customer;
import ripley.banco.interview.technicaltest.exceptions.CustomerNotFoundException;
import ripley.banco.interview.technicaltest.exceptions.ForbiddenCheckCharacterException;
import ripley.banco.interview.technicaltest.exceptions.NotValidCheckDigitException;
import ripley.banco.interview.technicaltest.exceptions.RutAreNotDigitsException;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomerMockData();

    Customer findCustomer(String rut) throws CustomerNotFoundException, NotValidCheckDigitException,
            ForbiddenCheckCharacterException, RutAreNotDigitsException;
}
