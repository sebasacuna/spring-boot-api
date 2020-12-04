package ripley.banco.interview.technicaltest.wsrest.error;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Builder
@Data
public class ApiError {

    private HttpStatus status;
    private String timestamp;
    private String message;

}
