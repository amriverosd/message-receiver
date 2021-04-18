package es.meli.controller.segmented.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SegmentedRequest implements Serializable {

  @Schema(description = "Satellites distance to receptor")
  @NotNull
  private double distance;

  @Schema(description = "Satellites list message")
  @NotNull
  private List<String> message;

}
