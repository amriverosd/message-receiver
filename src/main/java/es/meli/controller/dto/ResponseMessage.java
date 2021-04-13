package es.meli.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseMessage {

  private String message;
  
  private double x;
  
  private double y;
  
  
}
