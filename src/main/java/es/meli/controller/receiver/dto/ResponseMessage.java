package es.meli.controller.receiver.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseMessage {

  private String message;
  
  private PositionResponse position;
  
}
