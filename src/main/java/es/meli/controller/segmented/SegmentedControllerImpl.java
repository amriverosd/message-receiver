package es.meli.controller.segmented;

import es.meli.controller.receiver.dto.ResponseMessage;
import es.meli.controller.segmented.dto.SegmentedRequest;
import es.meli.model.SatelliteNameEnum;
import es.meli.usecase.segmented.SegmentedService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SegmentedControllerImpl implements SegmentedController {
  
  private final SegmentedService segmentedService;
  
  @Override
  public void saveSatelliteInfo(SatelliteNameEnum satelliteName, SegmentedRequest request) {
    segmentedService.saveSatellite(satelliteName, request);
  }

  @Override
  public ResponseMessage analizeMessage() {
    return segmentedService.processSavedInformation();
  }

  @Override
  public void deleteSatelliteInfo(SatelliteNameEnum satelliteName) {
    segmentedService.delete(satelliteName);    
  }


}
