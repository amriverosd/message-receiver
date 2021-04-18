package es.meli.usecase.receiver;

import es.meli.controller.receiver.dto.RequestMessage;
import es.meli.controller.receiver.dto.ResponseMessage;

@FunctionalInterface
public interface MessageReceiverService {

  /**
   * Gets the response (message and location) based on request. 
   * @param request.
   * @return response.
   */
  ResponseMessage processMessage(RequestMessage request);

}
