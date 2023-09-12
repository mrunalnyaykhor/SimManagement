package com.sim.management.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class GlobalExceptionHandler {
    @ExceptionHandler({CustomerException.class,SimException.class})
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(CustomerException customerException, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), List.of(customerException.getMessage()),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException,
                                                               WebRequest webRequest){
        List<String> collect = methodArgumentNotValidException.getBindingResult().getAllErrors().stream().filter(Objects::nonNull).map(
                DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

        ErrorDetails errorDetails = new ErrorDetails(new Date(),collect,"Invalid Request");
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

}
