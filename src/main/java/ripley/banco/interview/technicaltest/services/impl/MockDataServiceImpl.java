package ripley.banco.interview.technicaltest.services.impl;

import org.springframework.stereotype.Service;
import ripley.banco.interview.technicaltest.dtos.Customer;
import ripley.banco.interview.technicaltest.services.MockDataService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class MockDataServiceImpl implements MockDataService {

    public List<Customer> getCustomerTestData() {

        Customer sebastian = Customer.builder()
                .name("Sebastian")
                .rut("173459629")
                .lastName("Acuna").
                        birthdate(LocalDate.now().toString()).
                        build();

        Customer carlos = Customer.builder()
                .name("Carlos")
                .rut("123456456")
                .lastName("Ruiz").
                        birthdate(LocalDate.now().toString()).
                        build();

        Customer jose = Customer.builder()
                .name("Jose")
                .rut("789878456")
                .lastName("Echeverria").
                        birthdate(LocalDate.now().toString()).
                        build();

        Customer cristian = Customer.builder()
                .name("Cristian")
                .rut("489565")
                .lastName("Gallardo").
                        birthdate(LocalDate.now().toString()).
                        build();

        return Arrays.asList(
                sebastian, carlos, jose, cristian);
    }


}
