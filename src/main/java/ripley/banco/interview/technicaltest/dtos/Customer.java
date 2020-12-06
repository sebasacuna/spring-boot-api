package ripley.banco.interview.technicaltest.dtos;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Customer implements Serializable {

    private String rut;
    private String name;
    private String lastName;
    private String birthdate;

}
