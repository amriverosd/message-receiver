package es.meli.controller;

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
import es.meli.controller.receiver.MessageReceiverControllerImpl;
import es.meli.controller.receiver.dto.RequestMessage;
import es.meli.controller.receiver.dto.ResponseMessage;
import es.meli.usecase.receiver.MessageReceiverService;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest extends BaseUTest {
  
  @Mock
  private MessageReceiverService messageReceiverService;
  
  @InjectMocks
  private MessageReceiverControllerImpl messageReceiverController;
  
  
  @Test
  public void analizeMessageTest() {
    RequestMessage request = getMessageResquestMock();
    ResponseMessage responseMock = buildMockResponse();
    
    when(messageReceiverService.processMessage(request))
        .thenReturn(responseMock);

    ResponseMessage response = messageReceiverController.analizeMessage(request);
    assertNotNull(response);
    assertEquals(MESSAGE_TEST, response.getMessage());
    verify(messageReceiverService, times(1)).processMessage(request);
  }
  
}
