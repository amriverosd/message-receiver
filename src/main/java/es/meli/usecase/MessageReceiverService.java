package es.meli.usecase;

import es.meli.controller.dto.RequestMessage;
import es.meli.controller.dto.ResponseMessage;

@FunctionalInterface
public interface MessageReceiverService {

  ResponseMessage processMessage(RequestMessage request);

}
