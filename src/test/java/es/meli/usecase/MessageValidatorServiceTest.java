package es.meli.usecase;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import es.meli.BaseUTest;
import es.meli.controller.receiver.dto.RequestMessage;
import es.meli.exception.UnprocessableMessage;
import es.meli.usecase.message.MessageValidatorServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class MessageValidatorServiceTest extends BaseUTest {

  @InjectMocks
  public MessageValidatorServiceImpl messageValidatorService;

  @Test
  public void processMessageTest() {
    RequestMessage request = getMessageResquestMock();
    String message = messageValidatorService.processMessage(request);
    assertNotNull(message);
    assertEquals("Este es un mensaje de prueba ", message);
  }

  @Test(expected = UnprocessableMessage.class)
  public void processMessageTestError() {
    RequestMessage request = getMessageResquestMock();
    request.getSatellites().get(2).setMessage(Arrays.asList(new String[]{"", "", "un", "", "", ""}));
    messageValidatorService.processMessage(request);
  }
  
}