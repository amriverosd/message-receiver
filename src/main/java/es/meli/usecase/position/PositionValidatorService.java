package es.meli.usecase.position;

import es.meli.controller.receiver.dto.PositionResponse;
import es.meli.controller.receiver.dto.RequestMessage;

@FunctionalInterface
public interface PositionValidatorService {

  /**
   * Returns the location or an error based on the request. 
   * @param request.
   * @return X and Y position. 
   */
  PositionResponse getLocation(RequestMessage request);

}
