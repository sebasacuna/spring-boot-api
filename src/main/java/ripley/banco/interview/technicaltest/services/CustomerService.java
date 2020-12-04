package ripley.banco.interview.technicaltest.services;

import ripley.banco.interview.technicaltest.dtos.Customer;
import ripley.banco.interview.technicaltest.exceptions.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomerMockData();

    Customer findCustomer(String rut) throws CustomerNotFoundException, Exception;
}
