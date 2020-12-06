package ripley.banco.interview.technicaltest.wsrest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ripley.banco.interview.technicaltest.dtos.Customer;
import ripley.banco.interview.technicaltest.exceptions.CustomerNotFoundException;
import ripley.banco.interview.technicaltest.exceptions.ForbiddenControlCharacterException;
import ripley.banco.interview.technicaltest.exceptions.NotValidControlDigitException;
import ripley.banco.interview.technicaltest.exceptions.RutAreNotDigitsException;
import ripley.banco.interview.technicaltest.services.CustomerService;
import ripley.banco.interview.technicaltest.wsrest.error.RestExceptionHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class WSRestCustomerTest {

    @InjectMocks
    WSRestCustomer wsRestCustomer;

    private MockMvc mockMvc;

    @Mock
    WSRestCustomer wsRestCustomerMock;

    @Mock
    CustomerService customerService;

    public static final String API_GET_CUSTOMER = "/api/customer/anyrut";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(wsRestCustomerMock)
                .setControllerAdvice(new RestExceptionHandler())
                .build();
    }

    @Test
    public void getCustomer_shouldReturn200Status() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(customerService.findCustomer(any(String.class)))
                .thenReturn(any(Customer.class));

        String anyRut = "anyRut";

        ResponseEntity<Customer> responseEntity = wsRestCustomer.getCustomer(anyRut);

        assertEquals(responseEntity.getStatusCode().value(), HttpStatus.OK.value());

    }

    @Test
    public void getCustomer_shouldReturn404CodeWhenThrowCustomerNotFoundException() throws Exception {

        Mockito.when(wsRestCustomerMock.getCustomer(any(String.class)))
                .thenThrow(CustomerNotFoundException.class);

        mockMvc.perform(get(API_GET_CUSTOMER)).andExpect(status().is(HttpStatus.NOT_FOUND.value()));

    }

    @Test
    public void getCustomer_shouldReturn400WhenThrowRutAreNotDigitsException() throws Exception {

        Mockito.when(wsRestCustomerMock.getCustomer(any(String.class)))
                .thenThrow(RutAreNotDigitsException.class);

        mockMvc.perform(get(API_GET_CUSTOMER)).andExpect(status().is(HttpStatus.BAD_REQUEST.value()));

    }

    @Test
    public void getCustomer_shouldReturn400WhenThrowControlCharacterContainForbiddenCharactersException() throws Exception {

        Mockito.when(wsRestCustomerMock.getCustomer(any(String.class)))
                .thenThrow(ForbiddenControlCharacterException.class);

        mockMvc.perform(get(API_GET_CUSTOMER)).andExpect(status().is(HttpStatus.BAD_REQUEST.value()));

    }

    @Test
    public void getCustomer_shouldReturn400WhenThrowNotValidControlDigitException() throws Exception {

        Mockito.when(wsRestCustomerMock.getCustomer(any(String.class)))
                .thenThrow(NotValidControlDigitException.class);

        mockMvc.perform(get(API_GET_CUSTOMER)).andExpect(status().is(HttpStatus.BAD_REQUEST.value()));

    }

}
