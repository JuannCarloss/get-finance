package br.com.getfinance.resources;

import br.com.getfinance.exceptions.NotFoundException;
import br.com.getfinance.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException404(
            NotFoundException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("erro", ex.getMessage());
        return ResponseEntity.status(404).body(errors);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions422(
            ValidationException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("erro", ex.getMessage());
        return ResponseEntity.status(422).body(errors);
    }
}
