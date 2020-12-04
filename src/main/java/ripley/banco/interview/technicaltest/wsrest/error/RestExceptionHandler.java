package ripley.banco.interview.technicaltest.wsrest.error;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ripley.banco.interview.technicaltest.exceptions.*;
import ripley.banco.interview.technicaltest.utils.DateUtils;

import java.time.LocalDateTime;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle customerNotFoundException from services
     *
     * @param customerNotFoundException
     * @return
     */
    @ExceptionHandler(CustomerNotFoundException.class)
    protected ResponseEntity<Object> CustomerNotFoundException(
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
     * @return
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(
            CustomerNotFoundException exception) {
        ApiError apiError = ApiError.builder()
                .message(exception.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .timestamp(DateUtils.localDateTimeToFormatDate(LocalDateTime.now()))
                .build();

        return buildResponseEntity(apiError);
    }

    /**
     * Handle RutAreNotDigitsException from services
     *
     * @param rutAreNotDigitsException
     * @return
     */
    @ExceptionHandler(RutAreNotDigitsException.class)
    protected ResponseEntity<Object> handleControlDigitContainLetterException(
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
     * @return
     */
    @ExceptionHandler(ControlCharacterContainLetterException.class)
    protected ResponseEntity<Object> handleControlDigitContainLetterException(
            ControlCharacterContainLetterException controlDigitContainLetterException) {
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
     * @return
     */
    @ExceptionHandler(NotValidControlDigitException.class)
    protected ResponseEntity<Object> handleNotValidControlDigitException(
            NotValidControlDigitException notValidControlDigitException) {
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
     * @return
     */
    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }



}
