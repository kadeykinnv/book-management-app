package kadeykin.example.bookmanagement.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
@RestController
@ResponseBody //
public class DefaultAdvice extends ResponseEntityExceptionHandler {
  @ExceptionHandler(value = {ResourceNotFoundException.class})
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public ExceptionResponse resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
    ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
    return response;
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }
}
