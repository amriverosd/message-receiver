package es.meli.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import es.meli.BaseUTest;
import es.meli.controller.segmented.SegmentedControllerImpl;
import es.meli.model.SatelliteNameEnum;
import es.meli.usecase.segmented.SegmentedService;

@RunWith(MockitoJUnitRunner.class)
public class SegmentedControllerTest extends BaseUTest {

  @Mock
  private SegmentedService segmentedService;
  
  @InjectMocks
  private SegmentedControllerImpl segmentedControllerImpl;
  

  @Test
  public void saveSatelliteInfoTest() {
    segmentedControllerImpl.saveSatelliteInfo(SatelliteNameEnum.kenobi, getSegmentedRequest());
    verify(segmentedService, times(1)).saveSatellite(SatelliteNameEnum.kenobi, getSegmentedRequest());
  }
  
  @Test
  public void analizeSegmentedMessageTest() {}
  
  @Test
  public void deleteSatelliteInfoTest() {
    segmentedControllerImpl.deleteSatelliteInfo(SatelliteNameEnum.kenobi);
    verify(segmentedService, times(1)).delete(SatelliteNameEnum.kenobi);
   }

}
