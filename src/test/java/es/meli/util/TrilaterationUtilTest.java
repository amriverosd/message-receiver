package es.meli.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import es.meli.exception.UnprocessableMessage;
import es.meli.model.SatelliteNameEnum;

@RunWith(MockitoJUnitRunner.class)
public class TrilaterationUtilTest {

  @InjectMocks
  public TrilaterationUtil trilaterationUtil;

  @Before
  public void setUp() {
    ReflectionTestUtils.setField(trilaterationUtil, "kenobi", new double[] { 100, 100 });
    ReflectionTestUtils.setField(trilaterationUtil, "sato", new double[] { 160, 120 });
    ReflectionTestUtils.setField(trilaterationUtil, "skywalker", new double[] { 70, 150 });    
  }
  
  @Test
  public void getLocationTest() {
    double[] location = trilaterationUtil.getLocation(getMock());
    assertNotNull(location);
    assertEquals(129.99946023524532, location[0], "Check the coordenate X");
    assertEquals(140.00207347830315, location[1], "Check the coordenate Y");
  }
  
  @Test(expected = UnprocessableMessage.class)
  public void getLocationWithOnePoint() {
    Map<SatelliteNameEnum, Double> mockRequest = getMock();
    mockRequest.remove(SatelliteNameEnum.skywalker);
    mockRequest.remove(SatelliteNameEnum.sato);    
    trilaterationUtil.getLocation(mockRequest );
  }  
  
  private Map<SatelliteNameEnum, Double> getMock() {
    Map<SatelliteNameEnum, Double> mockRequest = new HashMap<>();
    mockRequest.put(SatelliteNameEnum.kenobi, 50.0);
    mockRequest.put(SatelliteNameEnum.sato, 36.06);
    mockRequest.put(SatelliteNameEnum.skywalker, 60.83);
    return mockRequest;
  }

}
