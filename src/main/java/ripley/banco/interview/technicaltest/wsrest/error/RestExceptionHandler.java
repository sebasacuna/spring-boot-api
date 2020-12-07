package ripley.banco.interview.technicaltest.wsrest.error;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ripley.banco.interview.technicaltest.exceptions.CustomerNotFoundException;
import ripley.banco.interview.technicaltest.exceptions.ForbiddenCheckCharacterException;
import ripley.banco.interview.technicaltest.exceptions.NotValidCheckDigitException;
import ripley.banco.interview.technicaltest.exceptions.RutAreNotDigitsException;
import ripley.banco.interview.technicaltest.utils.DateUtils;

import java.time.LocalDateTime;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle customerNotFoundException from services
     *
     * @param customerNotFoundException
     * @return ApiError
     */
    @ExceptionHandler(CustomerNotFoundException.class)
    protected ResponseEntity<ApiError> customerNotFoundException(
            CustomerNotFoundException customerNotFoundException) {
        ApiError apiError = ApiError.builder()
                .message(customerNotFoundException.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .timestamp(DateUtils.localDateTimeToFormatDate(LocalDateTime.now()))
                .build();

        return buildResponseEntity(apiError);
    }


    /**
     * Handle exception from services
     *
     * @param exception
     * @return ApiError
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ApiError> handleException(
            CustomerNotFoundException exception) {
        ApiError apiError = ApiError.builder()
                .message(exception.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .timestamp(DateUtils.localDateTimeToFormatDate(LocalDateTime.now()))
                .build();

        return buildResponseEntity(apiError);
    }

    /**
     * Handle RutAreNotDigitsException from services
     *
     * @param rutAreNotDigitsException
     * @return ApiError
     */
    @ExceptionHandler(RutAreNotDigitsException.class)
    protected ResponseEntity<ApiError> handleControlDigitContainLetterException(
            RutAreNotDigitsException rutAreNotDigitsException) {
        ApiError apiError = ApiError.builder()
                .message(rutAreNotDigitsException.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(DateUtils.localDateTimeToFormatDate(LocalDateTime.now()))
                .build();

        return buildResponseEntity(apiError);
    }

    /**
     * Handle ControlDigitContainLetterException from services
     *
     * @param controlDigitContainLetterException
     * @return ApiError
     */
    @ExceptionHandler(ForbiddenCheckCharacterException.class)
    protected ResponseEntity<ApiError> handleControlDigitContainLetterException(
            ForbiddenCheckCharacterException controlDigitContainLetterException) {
        ApiError apiError = ApiError.builder()
                .message(controlDigitContainLetterException.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(DateUtils.localDateTimeToFormatDate(LocalDateTime.now()))
                .build();

        return buildResponseEntity(apiError);
    }

    /**
     * Handle NotValidControlDigitException from services
     *
     * @param notValidControlDigitException
     * @return ApiError
     */
    @ExceptionHandler(NotValidCheckDigitException.class)
    protected ResponseEntity<ApiError> handleNotValidControlDigitException(
            NotValidCheckDigitException notValidControlDigitException) {
        ApiError apiError = ApiError.builder()
                .message(notValidControlDigitException.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(DateUtils.localDateTimeToFormatDate(LocalDateTime.now()))
                .build();

        return buildResponseEntity(apiError);
    }

    /**
     * Builder class to create ResponseEntity with apiError Object
     *
     * @param apiError
     * @return ApiError
     */
    private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }


}
