package es.meli.usecase.position;

import es.meli.controller.receiver.dto.PositionResponse;
import es.meli.controller.receiver.dto.RequestMessage;
import es.meli.controller.receiver.dto.Satellites;
import es.meli.model.SatelliteNameEnum;
import es.meli.util.TrilaterationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PositionValidatorServiceImpl implements PositionValidatorService {
  
  private final TrilaterationUtil trilaterationUtil;

  @Override
  public PositionResponse getLocation(RequestMessage request) {
    Map<SatelliteNameEnum, Double> infoMap = request.getSatellites().stream()
        .collect(Collectors.toMap(Satellites::getName, Satellites::getDistance));
    log.info("Analyzing the positions for {} ", infoMap);
    double[] location = trilaterationUtil.getLocation(infoMap);
    return PositionResponse.builder().x(location[0]).y(location[1]).build(); 
  }

}
