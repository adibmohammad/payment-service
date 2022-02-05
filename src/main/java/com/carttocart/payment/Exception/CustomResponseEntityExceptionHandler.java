package com.carttocart.payment.Exception;

import com.carttocart.payment.model.dto.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<ErrorResponse> recordNotFoundExceptionHandler(RecordNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public final ResponseEntity<ErrorResponse> userNotFoundExceptionHandler(UsernameNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResourceAccessException.class)
    public final ResponseEntity<ErrorResponse> resourceAccessExceptionHandler(ResourceAccessException ex) {
        return new ResponseEntity<>(new ErrorResponse("communication error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
