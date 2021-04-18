package es.meli.usecase;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import es.meli.BaseUTest;
import es.meli.controller.receiver.dto.PositionResponse;
import es.meli.controller.receiver.dto.RequestMessage;
import es.meli.model.SatelliteNameEnum;
import es.meli.usecase.position.PositionValidatorServiceImpl;
import es.meli.util.TrilaterationUtil;

@RunWith(MockitoJUnitRunner.class)
public class PositionValidatorServiceTest extends BaseUTest {

  @Mock
  public TrilaterationUtil trilaterationUtil;
  
  @InjectMocks
  public PositionValidatorServiceImpl positionValidatorService;
  

  @Test
  public void getLocationTest() {
    RequestMessage request = getMessageResquestMock();    
    Map<SatelliteNameEnum, Double> mock = getMapMock();
    
    when(trilaterationUtil.getLocation(mock)).thenReturn(new double[] {12.0, 12.0});
    
    PositionResponse response = positionValidatorService.getLocation(request);
    assertNotNull(response);
    assertEquals(12.0, response.getX());
    assertEquals(12.0, response.getY());
    verify(trilaterationUtil, times(1)).getLocation(mock);
  }



  
}