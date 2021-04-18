package es.meli.usecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import es.meli.BaseUTest;
import es.meli.controller.segmented.dto.SegmentedRequest;
import es.meli.model.SatelliteNameEnum;
import es.meli.repository.redis.RedisRepository;
import es.meli.usecase.receiver.MessageReceiverService;
import es.meli.usecase.segmented.SegmentedServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class SegmentedServiceTest extends BaseUTest {

  @Mock
  public MessageReceiverService messageReceiverService;
  
  @Mock
  public RedisRepository redisService;
  
  @InjectMocks
  public SegmentedServiceImpl segmentedService;
  

  @Test
  public void saveSatelliteTest() {   
    SegmentedRequest request = new SegmentedRequest();
    segmentedService.saveSatellite(SatelliteNameEnum.sato, request);
    verify(redisService, times(1)).save(SatelliteNameEnum.sato, request);
  }
  
  @Test
  public void deleteSatelliteTest() {   
    segmentedService.delete(SatelliteNameEnum.sato);
    verify(redisService, times(1)).delete(SatelliteNameEnum.sato);
  }
  
  @Test
  public void processSavedInformationTest() {
    SegmentedRequest responseMock = getSegmentedRequest();    
    when(redisService.findByName(SatelliteNameEnum.sato)).thenReturn(null);
    when(redisService.findByName(SatelliteNameEnum.skywalker)).thenReturn(responseMock);
    
    segmentedService.processSavedInformation();   
    
    verify(redisService, times(3)).findByName( any() );
  }
  
}