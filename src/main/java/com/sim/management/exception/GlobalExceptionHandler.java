package com.sim.management.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value={SimException.class })
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(SimException simException, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), simException.getMessage(),HttpStatus.NOT_FOUND);
        webRequest.getDescription(false);
        return ResponseEntity.ok(errorDetails);
    }
//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException,
//                                                               WebRequest webRequest){
//        List<String> collect = methodArgumentNotValidException.getBindingResult().getAllErrors().stream().filter(Objects::nonNull).map(
//                DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
//
//        ErrorDetails errorDetails = new ErrorDetails(new Date(),collect,"Invalid Request");
//        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
//    }
    @ExceptionHandler(value={CustomerException.class})
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(CustomerException exception,
                                                                        WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),HttpStatus.NOT_FOUND);
                webRequest.getDescription(false);
        return ResponseEntity.ok(errorDetails);
    }
}
