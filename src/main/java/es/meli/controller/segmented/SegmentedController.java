package es.meli.controller.segmented;

import es.meli.controller.documentation.MessageReceiverDocumentation.ThirdLevel;
import es.meli.controller.documentation.MessageReceiverDocumentation.SegmentedDeleteSatellite;
import es.meli.controller.documentation.MessageReceiverDocumentation.SegmentedSaveSatellite;
import es.meli.controller.documentation.MessageReceiverDocumentation.SegmentedTopSecret;
import es.meli.controller.receiver.dto.ResponseMessage;
import es.meli.controller.segmented.dto.SegmentedRequest;
import es.meli.model.SatelliteNameEnum;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@ThirdLevel
@CrossOrigin
@RequestMapping(value = "/v1/services/", produces = MediaType.APPLICATION_JSON_VALUE)
public interface SegmentedController {

  @SegmentedSaveSatellite
  @PostMapping("topsecret_split/{satelliteName}")
  void saveSatelliteInfo(@PathVariable SatelliteNameEnum satelliteName,
      @RequestBody @Valid SegmentedRequest request); 

  @SegmentedTopSecret
  @GetMapping("topsecret_split")
  ResponseMessage analizeMessage();
  
  @SegmentedDeleteSatellite
  @DeleteMapping("topsecret_split/{satelliteName}")
  void deleteSatelliteInfo(@PathVariable SatelliteNameEnum satelliteName); 
  
}
