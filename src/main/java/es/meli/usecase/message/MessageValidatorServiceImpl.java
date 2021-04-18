package es.meli.usecase.message;

import es.meli.controller.receiver.dto.RequestMessage;
import es.meli.controller.receiver.dto.Satellites;
import es.meli.exception.UnprocessableMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageValidatorServiceImpl implements MessageValidatorService {
  
  @Override
  public String processMessage(RequestMessage request) {    
    List<List<String>> satelliteList = request.getSatellites().stream()
        .filter(element -> Objects.nonNull(element.getMessage())).map(Satellites::getMessage)
        .collect(Collectors.toList());    
    return getMessage(satelliteList);
  }

  /**
   * Gets the message based on an array of satellites.
   * @param satelliteList
   * @return message.
   */
  private String getMessage(List<List<String>> satelliteList) {
    log.info("Analyzing the information for {} ", satelliteList);
    int satellitesNumber = satelliteList.size();
    StringBuilder message = new StringBuilder();
    for (int i = 0; i < satelliteList.get(0).size(); i++) {
      message.append(findMessageByPosition(satellitesNumber, satelliteList, i));
      message.append(" ");
    }
    return message.toString();
  }

  /**
   * Finds a valid message for specific position.
   * @param satellitesNumber.
   * @param list.
   * @param position. 
   * @return message.
   */
  private String findMessageByPosition(int satellitesNumber, List<List<String>> list,
      int position) {
    for (int i = 0; i < satellitesNumber; i++) {
      if (StringUtils.isNotBlank(list.get(i).get(position))) {
        log.info("Message in position {} is returned by statellite {}", position, i);
        return list.get(i).get(position);
      }
    }
    log.error("Error getting the message in the position {}", position);
    throw new UnprocessableMessage(String.format("Incomplete message in position: %s", position));
  }

}
