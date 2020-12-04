package ripley.banco.interview.technicaltest.wsrest;

import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ripley.banco.interview.technicaltest.dtos.Customer;
import ripley.banco.interview.technicaltest.services.CustomerService;
import ripley.banco.interview.technicaltest.wsrest.error.ApiError;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = {"http://localhost:8080", "localhost:4300"})
@Api(tags = "Customers")
public class WSRestCustomer {

    private static final Logger logger = LoggerFactory.getLogger(WSRestCustomer.class);

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "This endpoint is used to get a customer by rut, code 200")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message =
                    "- Rut sequence must be only digits \t\n" +
                    "- Invalid control character, control character must be a numbers or character K \t\n" +
                    "- Control digit not corresponds to sequence of numbers  \t\n", response = ApiError.class),
            @ApiResponse(code = 404, message = "Customer not exist", response = ApiError.class)
    })
    @GetMapping(path = "/customer/{rut}", produces = "application/json")
    public ResponseEntity<Customer> getCustomer(@PathVariable final String rut) throws Exception {
        Customer customer = customerService.findCustomer(rut);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }


}
