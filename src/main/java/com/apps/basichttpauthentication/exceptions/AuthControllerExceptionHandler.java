package com.apps.basichttpauthentication.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AuthControllerExceptionHandler {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, List<String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exc){
        Map<String, List<String>> errors = new HashMap<>();
        List<String> internalErrors = exc.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        errors.put("errors", internalErrors);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RoleDoesNotExistException.class})
    public ResponseEntity<Map<String, List<String>>> handleRoleDoesNotExistException(RoleDoesNotExistException exc){
        Map<String, List<String>> errors = new HashMap<>();
        List<String> internalErrors = new ArrayList<>();
        internalErrors.add(exc.getMessage());
        errors.put("errors", internalErrors);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Map<String, List<String>>> handleDataIntegrityViolationException(DataIntegrityViolationException exc){
        Map<String, List<String>> errors = new HashMap<>();
        List<String> internalErrors = new ArrayList<>();
        internalErrors.add(exc.getMessage());
        errors.put("errors", internalErrors);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
