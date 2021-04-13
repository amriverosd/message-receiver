package es.meli.usecase;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import es.meli.controller.dto.RequestMessage;
import es.meli.controller.dto.ResponseMessage;
import es.meli.controller.dto.SatelliteNameEnum;
import es.meli.util.TrilaterationUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageReceiverServiceImpl implements MessageReceiverService {
  
  private final TrilaterationUtil trilaterationUtil;
  
  @Override
  public ResponseMessage processMessage(RequestMessage request) {

    List<Double> distances = request.getSatellites().stream()
        .map(satellite -> satellite.getDistance()).collect(Collectors.toList());
    Double[] array = distances.toArray(new Double[distances.size()]);
    double[] doubleArray = ArrayUtils.toPrimitive(array);
    double[] point = trilaterationUtil.getLocation(doubleArray);
//Check the order. 
    return ResponseMessage.builder().x(point[0]).y(point[1]).message(checkMessage(request)).build();

  }

  private String checkMessage(RequestMessage request) {
    StringBuilder message = new StringBuilder();
    
    List<String> kenobiMessages = request.getSatellites().stream()
        .filter(satellite -> SatelliteNameEnum.kenobi.equals(satellite.getName())).findAny().get()
        .getMessage();
    List<String> skywalkerMessages = request.getSatellites().stream()
        .filter(satellite -> SatelliteNameEnum.skywalker.equals(satellite.getName())).findAny().get()
        .getMessage();
    List<String> satoMessages = request.getSatellites().stream()
        .filter(satellite -> SatelliteNameEnum.sato.equals(satellite.getName())).findAny().get()
        .getMessage();
    
    for (int i = 0; i < kenobiMessages.size(); i++) {
      if(StringUtils.isNotBlank(kenobiMessages.get(i))  ) {
        message.append(kenobiMessages.get(i));
      }else {
        if(StringUtils.isNotBlank(skywalkerMessages.get(i))) {
          message.append(skywalkerMessages.get(i));
        }else {
          if(StringUtils.isNotBlank(satoMessages.get(i))) {
            message.append(satoMessages.get(i));
          }else {
            //new ResponseEntity<>(HttpStatus.NOT_FOUND); 
          }
        }
      }
      message.append(" ");
    }
    
    return message.toString();
  }
  
}
