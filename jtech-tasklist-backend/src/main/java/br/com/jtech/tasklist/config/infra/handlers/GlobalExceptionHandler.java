package br.com.jtech.tasklist.config.infra.handlers;

import br.com.jtech.tasklist.config.infra.exceptions.*;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Global exception handler for the API.
 *
 * @author angelo.vicente
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest request) {
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST);
        error.setMessage("Validation failed");
        error.setTimestamp(LocalDateTime.now());
        error.setPath(request.getRequestURI());
        error.setSubErrors(subErrors(ex));
        error.setDebugMessage(ex.getLocalizedMessage());
        return buildResponseEntity(error);
    }

    @ExceptionHandler(TasklistNotFoundException.class)
    public ResponseEntity<ApiError> handleTasklistNotFound(TasklistNotFoundException ex, HttpServletRequest request) {
        ApiError error = new ApiError(HttpStatus.NOT_FOUND);
        error.setMessage(ex.getMessage());
        error.setTimestamp(LocalDateTime.now());
        error.setPath(request.getRequestURI());
        error.setDebugMessage(ex.getLocalizedMessage());
        return buildResponseEntity(error);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequest(BadRequestException ex, HttpServletRequest request) {
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST);
        error.setMessage(ex.getMessage());
        error.setTimestamp(LocalDateTime.now());
        error.setPath(request.getRequestURI());
        error.setDebugMessage(ex.getLocalizedMessage());
        return buildResponseEntity(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception ex, HttpServletRequest request) {
        ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
        error.setMessage("Unexpected error occurred");
        error.setTimestamp(LocalDateTime.now());
        error.setPath(request.getRequestURI());
        error.setDebugMessage(ex.getMessage());
        return buildResponseEntity(error);
    }

    private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    private List<ApiSubError> subErrors(MethodArgumentNotValidException ex) {
        List<ApiSubError> errors = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            ApiValidationError api = new ApiValidationError(
                    ex.getObjectName(),
                    fieldError.getField(),
                    fieldError.getRejectedValue(),
                    fieldError.getDefaultMessage()
            );
            errors.add(api);
        }
        return errors;
    }
}
