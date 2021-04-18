package es.meli.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class UnprocessableMessage extends RuntimeException {

  private static final long serialVersionUID = -8488823675570304053L;
  
  private final HttpStatus status;

  public UnprocessableMessage(String message, Throwable cause) {
    super(message, cause);
    this.status = HttpStatus.NOT_FOUND;
  }

  public UnprocessableMessage(String message) {
    super(message);
    this.status = HttpStatus.NOT_FOUND;
  }


}
