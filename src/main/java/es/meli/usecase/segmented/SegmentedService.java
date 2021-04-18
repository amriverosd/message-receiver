package es.meli.usecase.segmented;

import es.meli.controller.receiver.dto.ResponseMessage;
import es.meli.controller.segmented.dto.SegmentedRequest;
import es.meli.model.SatelliteNameEnum;

public interface SegmentedService {
  
  /**
   * Saves the satellite info in redis. 
   * @param satelliteName
   * @param request
   */
  void saveSatellite(SatelliteNameEnum satelliteName, SegmentedRequest request);

  /**
   * Deletes the satellite info.
   * @param satelliteName
   */
  void delete(SatelliteNameEnum satelliteName);
  
  /**
   * Process the information with the stored information. 
   * @return response. 
   */
  ResponseMessage processSavedInformation();

}
