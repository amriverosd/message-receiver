package es.meli.usecase.receiver;

import es.meli.controller.receiver.dto.RequestMessage;
import es.meli.controller.receiver.dto.ResponseMessage;
import es.meli.usecase.message.MessageValidatorService;
import es.meli.usecase.position.PositionValidatorService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageReceiverServiceImpl implements MessageReceiverService {
  
  private final PositionValidatorService positionValidatorService;
  
  private final MessageValidatorService messageValidatorService;
  
  @Override
  public ResponseMessage processMessage(RequestMessage request) {
    return ResponseMessage.builder().message(messageValidatorService.processMessage(request))
        .position(positionValidatorService.getLocation(request)).build();
  }  

}