package de.axontic.challenge.shoppingcartmanagement.exception;

import de.axontic.challenge.shoppingcartmanagement.dto.ResponseObject;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({RecordNotFoundException.class})
    public final ResponseEntity<Object> handleEntityNotFoundException(RuntimeException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ResponseObject<Object> responseObject = ResponseObject.builder()
                .status(status.value())
                .message(ex.getMessage())
                .build();

        log.error("{} request to URI: [{}] failed. The Reason is: {}", request.getMethod(), request.getRequestURI(), ex.getMessage());
        return new ResponseEntity<>(responseObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadRequestException.class, UnexpectedServerException.class })
    public final ResponseEntity<Object> handleEntityConflictingResultOperations(RuntimeException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ResponseObject<Object> responseObject = ResponseObject.builder()
                .status(status.value())
                .message(ex.getMessage())
                .build();

        log.error("{} request to URI: [{}] failed. The Reason is: {}", request.getMethod(), request.getRequestURI(), ex.getMessage());
        return new ResponseEntity<>(responseObject, status);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ResponseObject responseObject = ResponseObject.builder()
                .status(statusCode.value())
                .message("Validation failed")
                .data(errors)
                .build();


        log.error("{} request to URI: [{}] failed. The Reason is: {}", ((ServletWebRequest) request).getNativeRequest(HttpServletRequest.class).getMethod(), ((ServletWebRequest) request).getNativeRequest(HttpServletRequest.class).getRequestURI(), responseObject.getMessage());
        return handleExceptionInternal(ex, responseObject, headers, statusCode, request);
    }

}
