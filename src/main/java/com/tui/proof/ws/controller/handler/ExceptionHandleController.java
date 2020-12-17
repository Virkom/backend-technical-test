package com.tui.proof.ws.controller.handler;

import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tui.proof.ws.dto.ErrorDescription;
import com.tui.proof.ws.dto.ErrorResponse;
import com.tui.proof.ws.exception.ObjectNotFoundException;
import com.tui.proof.ws.exception.UserLoginException;

@Slf4j
@RestControllerAdvice
public class ExceptionHandleController {

    @ExceptionHandler(UserLoginException.class)
    public ResponseEntity<ErrorResponse> handleLoginException(UserLoginException e) {
        ErrorDescription errorDescription = new ErrorDescription("Authentication", e.getMessage());
        return new ResponseEntity<>(new ErrorResponse(List.of(errorDescription)), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ErrorResponse errorResponse = new ErrorResponse(extractErrorFieldsWithMessages(ex.getBindingResult()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleObjectNotFoundException(ObjectNotFoundException e) {
        ErrorDescription errorDescription = new ErrorDescription("Object", e.getMessage());
        return new ResponseEntity<>(new ErrorResponse(List.of(errorDescription)), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleObjectNotFoundException(RuntimeException e) {
        log.error(e.getMessage(), e);
        ErrorDescription errorDescription = new ErrorDescription("Server", e.getMessage());
        return new ResponseEntity<>(new ErrorResponse(List.of(errorDescription)), HttpStatus.NOT_FOUND);
    }

    private List<ErrorDescription> extractErrorFieldsWithMessages(BindingResult bindingResult) {
        return bindingResult
                .getFieldErrors()
                .stream()
                .map(fieldError -> new ErrorDescription(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
    }
}
