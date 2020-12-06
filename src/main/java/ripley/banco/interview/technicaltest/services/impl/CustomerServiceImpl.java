package ripley.banco.interview.technicaltest.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ripley.banco.interview.technicaltest.dtos.Customer;
import ripley.banco.interview.technicaltest.exceptions.CustomerNotFoundException;
import ripley.banco.interview.technicaltest.exceptions.ForbiddenControlCharacterException;
import ripley.banco.interview.technicaltest.exceptions.NotValidControlDigitException;
import ripley.banco.interview.technicaltest.exceptions.RutAreNotDigitsException;
import ripley.banco.interview.technicaltest.services.CustomerService;
import ripley.banco.interview.technicaltest.services.MockDataService;
import ripley.banco.interview.technicaltest.utils.RutUtils;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private MockDataService mockDataService;

    @Autowired
    public void setMockDataService(MockDataService mockDataService) {
        this.mockDataService = mockDataService;
    }

    @Override
    public List<Customer> getCustomerMockData() {
        return mockDataService.getCustomerTestData();
    }

    @Override
    public Customer findCustomer(String rut) throws CustomerNotFoundException,
            NotValidControlDigitException, ForbiddenControlCharacterException, RutAreNotDigitsException {

        RutUtils.isValidRut(rut.toUpperCase());

        List<Customer> customers = getCustomerMockData();

        Optional<Customer> customerToFind = customers.parallelStream().
                filter(customer -> customer.getRut().equals(rut))
                .findFirst();

        if (customerToFind.isPresent()) {
            String customerInfo = customerToFind.get().toString();
            logger.info("customer found, info customer: {}", customerInfo);
            return customerToFind.get();
        } else {
            logger.error("Customer not exist, looking for rut {}", rut);
            throw new CustomerNotFoundException();
        }

    }

}
