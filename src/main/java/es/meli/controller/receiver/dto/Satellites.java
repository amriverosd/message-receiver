package es.meli.controller.receiver.dto;

import es.meli.controller.segmented.dto.SegmentedRequest;
import es.meli.model.SatelliteNameEnum;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Satellites extends SegmentedRequest {
  
  @Schema(description = "Satellites name")
  @NotBlank
  @Valid
  private SatelliteNameEnum name;
  
}
