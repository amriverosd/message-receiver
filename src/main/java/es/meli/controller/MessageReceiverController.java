package es.meli.controller;

import es.meli.controller.documentation.MessageReceiverDocumentation.SecondLevel;
import es.meli.controller.documentation.MessageReceiverDocumentation.TopSecret;
import es.meli.controller.dto.RequestMessage;
import es.meli.controller.dto.ResponseMessage;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@SecondLevel
@RequestMapping(value = "/services/", produces = MediaType.APPLICATION_JSON_VALUE)
public interface MessageReceiverController {

  @TopSecret
  @PostMapping("topsecret")
  ResponseMessage analizeMessage(@RequestBody @Valid  RequestMessage request);

}
