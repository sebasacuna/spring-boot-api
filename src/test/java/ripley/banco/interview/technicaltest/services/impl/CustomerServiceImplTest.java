package ripley.banco.interview.technicaltest.services.impl;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import ripley.banco.interview.technicaltest.dtos.Customer;
import ripley.banco.interview.technicaltest.exceptions.CustomerNotFoundException;
import ripley.banco.interview.technicaltest.exceptions.ForbiddenCheckCharacterException;
import ripley.banco.interview.technicaltest.exceptions.NotValidCheckDigitException;
import ripley.banco.interview.technicaltest.exceptions.RutAreNotDigitsException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CustomerServiceImplTest {

    private CustomerServiceImpl customerServiceImpl;

    @BeforeEach
    public void setup() throws Exception {
        customerServiceImpl = new CustomerServiceImpl();
        customerServiceImpl.setMockDataService(new MockDataServiceImpl());
    }

    @ParameterizedTest
    @ValueSource(strings = {"173459629", "235564785", "51310977", "160184094"})
    public void findCustomer_shouldFindCustomers(String rut) throws CustomerNotFoundException,
            NotValidCheckDigitException, ForbiddenCheckCharacterException, RutAreNotDigitsException {

        Optional<Customer> customer = Optional.ofNullable(customerServiceImpl.findCustomer(rut));

        assertTrue(customer.isPresent());

    }

    @ParameterizedTest
    @ValueSource(strings = {"223349676", "215194884", "82204687", "210297243"})
    public void findCustomer_shouldNotFindCustomersAndThrowCustomerNotFoundException(String rut) {

        CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class, () -> {
            customerServiceImpl.findCustomer(rut);
        });

        String expectedMessage = "Customer not exist";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @ParameterizedTest
    @ValueSource(strings = {"2x334g676", "!5194v84", "822*4687", "abcdefg3"})
    public void findCustomer_shouldThrowRutAreNotDigitsException(String rut) {

        RutAreNotDigitsException exception = assertThrows(RutAreNotDigitsException.class, () -> {
            customerServiceImpl.findCustomer(rut);
        });

        String expectedMessage = "Rut sequence must be only digits";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @ParameterizedTest
    @ValueSource(strings = {"17345962A", "23556478!", "5131097c", "16018409*"})
    public void findCustomer_shouldThrowForbiddenCheckCharacterException(String rut) {

        ForbiddenCheckCharacterException exception = assertThrows(ForbiddenCheckCharacterException.class, () -> {
            customerServiceImpl.findCustomer(rut);
        });

        String expectedMessage = "Invalid check character, check character must be a numbers or character K";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @ParameterizedTest
    @ValueSource(strings = {"173459621", "235564782", "51310973", "16018409K"})
    public void findCustomer_shouldThrowNotValidControlDigitException(String rut) {

        NotValidCheckDigitException exception = assertThrows(NotValidCheckDigitException.class, () -> {
            customerServiceImpl.findCustomer(rut);
        });

        String expectedMessage = "Check digit does not corresponds to the sequence of numbers";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }


}
