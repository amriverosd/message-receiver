package es.meli.usecase;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import es.meli.BaseUTest;
import es.meli.controller.receiver.dto.PositionResponse;
import es.meli.controller.receiver.dto.RequestMessage;
import es.meli.controller.receiver.dto.ResponseMessage;
import es.meli.usecase.message.MessageValidatorService;
import es.meli.usecase.position.PositionValidatorService;
import es.meli.usecase.receiver.MessageReceiverServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class MessageReceiverServiceTest extends BaseUTest {

  @Mock
  private PositionValidatorService positionValidatorService;
  
  @Mock
  private MessageValidatorService messageValidatorService;
  
  @InjectMocks
  public MessageReceiverServiceImpl messageReceiverService;
  

  @Test
  public void getLocationTest() {
    RequestMessage request = getMessageResquestMock();

    when(positionValidatorService.getLocation(request))
        .thenReturn(PositionResponse.builder().x(X).y(Y).build());
    when(messageValidatorService.processMessage(request)).thenReturn(MESSAGE_TEST);

    ResponseMessage response = messageReceiverService.processMessage(request);
    assertNotNull(response);
    assertEquals(X, response.getPosition().getX());
    assertEquals(Y, response.getPosition().getY());
    assertEquals(MESSAGE_TEST, response.getMessage());
    verify(positionValidatorService, times(1)).getLocation(request);
    verify(messageValidatorService, times(1)).processMessage(request);
  }



  
}