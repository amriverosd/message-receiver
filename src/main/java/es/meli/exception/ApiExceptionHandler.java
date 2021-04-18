package es.meli.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler({ UnprocessableMessage.class })
  protected ResponseEntity<ApiErrorResponse> handleApiException(UnprocessableMessage exception) {
    return new ResponseEntity<>(new ApiErrorResponse(exception.getStatus(), exception.getMessage()),
        exception.getStatus());
  }
}
