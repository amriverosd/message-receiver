package es.meli.controller.receiver.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PositionResponse {

  private double x;

  private double y;

}
