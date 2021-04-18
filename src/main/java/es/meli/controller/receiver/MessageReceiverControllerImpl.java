package es.meli.controller.receiver;

import es.meli.controller.receiver.dto.RequestMessage;
import es.meli.controller.receiver.dto.ResponseMessage;
import es.meli.usecase.receiver.MessageReceiverService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageReceiverControllerImpl implements MessageReceiverController {

  private final MessageReceiverService messageReceiverService;
  
  @Override
  public ResponseMessage analizeMessage(RequestMessage request) {
    return messageReceiverService.processMessage(request);
  }

}
