package my.survey.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<?> handleException1(Exception e) {
        return ResponseEntity
                .badRequest()
                .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8))
                .body(List.of(Map.of("message", e.getMessage())));
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<?> handleException2(Exception e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8))
                .body(List.of(Map.of("message", e.getMessage())));
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<?> handleException3(ConstraintViolationException e) {
        return ResponseEntity.badRequest().body(
                e.getConstraintViolations().stream()
                        .map(violation -> Map.of(
                                        "message",
                                        violation.getMessage()
                                )
                        ));
    }
}
