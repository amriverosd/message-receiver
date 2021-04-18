package es.meli.usecase.message;

import es.meli.controller.receiver.dto.RequestMessage;

@FunctionalInterface
public interface MessageValidatorService {

  /**
   * Returns the message or an error based on the request. 
   * @param request.
   * @return message. 
   */
  String processMessage(RequestMessage request);

}
