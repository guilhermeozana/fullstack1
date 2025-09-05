package br.com.jtech.tasklist.config.infra.handlers;

import br.com.jtech.tasklist.config.infra.exceptions.ApiError;
import br.com.jtech.tasklist.config.infra.exceptions.ApiValidationError;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    @Test
    void shouldHandleValidationErrorsAndReturnApiError() {
        // Arrange
        GlobalExceptionHandler handler = new GlobalExceptionHandler();

        BindingResult bindingResult = mock(BindingResult.class);
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getRequestURI()).thenReturn("/api/test");
        when(exception.getObjectName()).thenReturn("UserDTO");

        FieldError fieldError = new FieldError(
                "UserDTO",
                "email",
                "invalid@email",
                false,
                null,
                null,
                "Invalid email"
        );

        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));
        when(exception.getBindingResult()).thenReturn(bindingResult);
        when(exception.getLocalizedMessage()).thenReturn("Validation failed");

        // Act
        ResponseEntity<ApiError> response = handler.handleValidationErrors(exception, request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        ApiError error = response.getBody();
        assertNotNull(error);
        assertEquals("Validation failed", error.getDebugMessage());
        assertEquals("/api/test", error.getPath());
        assertNotNull(error.getTimestamp());
        assertNotNull(error.getSubErrors());
        assertEquals(1, error.getSubErrors().size());

        ApiValidationError subError = (ApiValidationError) error.getSubErrors().get(0);
        assertEquals("UserDTO", subError.getObject());
        assertEquals("email", subError.getField());
        assertEquals("invalid@email", subError.getRejectedValue());
        assertEquals("Invalid email", subError.getMessage());
    }
}
