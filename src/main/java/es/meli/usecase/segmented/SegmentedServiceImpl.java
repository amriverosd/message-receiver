package es.meli.usecase.segmented;

import es.meli.controller.receiver.dto.RequestMessage;
import es.meli.controller.receiver.dto.ResponseMessage;
import es.meli.controller.receiver.dto.Satellites;
import es.meli.controller.segmented.dto.SegmentedRequest;
import es.meli.model.SatelliteNameEnum;
import es.meli.repository.redis.RedisRepository;
import es.meli.usecase.receiver.MessageReceiverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SegmentedServiceImpl implements SegmentedService {
  
  private final MessageReceiverService messageReceiverService;
  
  private final RedisRepository redisService;

  @Override
  public void saveSatellite(SatelliteNameEnum satelliteName, SegmentedRequest request) {
    log.info("Object saved {}: {}", satelliteName, request);
    redisService.save(satelliteName, request);
  }
  
  @Override
  public void delete(SatelliteNameEnum satelliteName) {
    redisService.delete(satelliteName);
  }

  @Override
  public ResponseMessage processSavedInformation() {
    List<Satellites> satelliteList = Arrays.stream(SatelliteNameEnum.values())
        .map(element -> map(element)).collect(Collectors.toList());
    List<Satellites> satellites = satelliteList.stream().filter(Objects::nonNull)
        .collect(Collectors.toList());
    return messageReceiverService
        .processMessage(RequestMessage.builder().satellites(satellites).build());
  }

  private Satellites map(SatelliteNameEnum element) {
    SegmentedRequest findByName = redisService.findByName(element);
    log.info("Recovered satellite {}: {}", element, findByName);
    if (Objects.nonNull(findByName)) {
      Satellites satellite = new Satellites();
      satellite.setDistance(findByName.getDistance());
      satellite.setMessage(findByName.getMessage());
      satellite.setName(element);
      return satellite;
    }
    return null;
  }


}
