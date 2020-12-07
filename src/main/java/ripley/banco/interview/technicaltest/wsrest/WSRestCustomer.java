package ripley.banco.interview.technicaltest.wsrest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ripley.banco.interview.technicaltest.dtos.Customer;
import ripley.banco.interview.technicaltest.exceptions.CustomerNotFoundException;
import ripley.banco.interview.technicaltest.exceptions.ForbiddenCheckCharacterException;
import ripley.banco.interview.technicaltest.exceptions.NotValidCheckDigitException;
import ripley.banco.interview.technicaltest.exceptions.RutAreNotDigitsException;
import ripley.banco.interview.technicaltest.services.CustomerService;
import ripley.banco.interview.technicaltest.wsrest.error.ApiError;

@RestController
@RequestMapping("api")
@CrossOrigin( origins = "*" )
@Api(tags = "Customers")
public class WSRestCustomer {

    private static final Logger logger = LoggerFactory.getLogger(WSRestCustomer.class);

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "This endpoint is used to get a customer by rut, must return code 200")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message =
                    "- Rut sequence must be only digits \t\n" +
                            "- Invalid control character, control character must be a numbers or character K \t\n" +
                            "- Check digit does not corresponds to the sequence of numbers  \t\n", response = ApiError.class),
            @ApiResponse(code = 404, message = "Customer not exist", response = ApiError.class)
    })
    @GetMapping(path = "/customer/{rut}", produces = "application/json")
    public ResponseEntity<Customer> getCustomer(@PathVariable final String rut) throws
            NotValidCheckDigitException, ForbiddenCheckCharacterException,
            CustomerNotFoundException, RutAreNotDigitsException {
        Customer customer = customerService.findCustomer(rut);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }


}
