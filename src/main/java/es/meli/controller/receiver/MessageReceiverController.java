package es.meli.controller.receiver;

import es.meli.controller.documentation.MessageReceiverDocumentation.SecondLevel;
import es.meli.controller.documentation.MessageReceiverDocumentation.TopSecret;
import es.meli.controller.receiver.dto.RequestMessage;
import es.meli.controller.receiver.dto.ResponseMessage;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@SecondLevel
@CrossOrigin
@RequestMapping(value = "/v1/services/", produces = MediaType.APPLICATION_JSON_VALUE)
public interface MessageReceiverController {

  @TopSecret
  @PostMapping("topsecret")
  ResponseMessage analizeMessage(@Valid @RequestBody RequestMessage request);

}
