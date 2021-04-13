package es.meli.controller;

import es.meli.controller.dto.RequestMessage;
import es.meli.controller.dto.ResponseMessage;
import es.meli.usecase.MessageReceiverService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageReceiverControllerImpl implements MessageReceiverController {

  private final MessageReceiverService messageReceiverService;
  
  @Override
  public ResponseMessage analizeMessage(RequestMessage request) {
    return null;
  }

}
