package es.meli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.meli.controller.receiver.dto.RequestMessage;
import es.meli.controller.receiver.dto.ResponseMessage;
import es.meli.controller.receiver.dto.Satellites;
import es.meli.controller.segmented.dto.SegmentedRequest;
import es.meli.model.SatelliteNameEnum;

public abstract class BaseUTest {

  public static final double KENOBI_DISTANCE = 60.83;
  
  public static final double SKYWALKER_DISTANCE = 36.06;
  
  public static final double SATO_DISTANCE = 50.0;

  public static final double Y = 21.0;

  public static final double X = 12.0;

  public static final String MESSAGE_TEST = "Este es un mensaje de prueba";

  public RequestMessage getMessageResquestMock() {
    List<Satellites> satellites = new ArrayList<>();
    Satellites kenobi = Satellites.builder().name(SatelliteNameEnum.kenobi).build();
    kenobi.setDistance(KENOBI_DISTANCE);
    kenobi.setMessage(Arrays.asList(new String[]{"Este", "", "un", "", "de", ""}) );
    satellites.add(kenobi);
    
    Satellites sato = Satellites.builder().name(SatelliteNameEnum.sato).build();
    sato.setMessage(Arrays.asList(new String[]{"", "es", "", "mensaje", "de", ""}) );
    sato.setDistance(SATO_DISTANCE);
    satellites.add(sato);
    
    Satellites skywalker = Satellites.builder().name(SatelliteNameEnum.skywalker).build();
    skywalker.setMessage(Arrays.asList(new String[]{"", "", "un", "", "", "prueba"}) );
    skywalker.setDistance(SKYWALKER_DISTANCE);
    satellites.add(skywalker);
    
    return RequestMessage.builder().satellites(satellites).build();
  }
  
  public Map<SatelliteNameEnum, Double> getMapMock() {
    Map<SatelliteNameEnum, Double> mock = new HashMap<>();
    mock.put(SatelliteNameEnum.sato, SATO_DISTANCE);
    mock.put(SatelliteNameEnum.skywalker, SKYWALKER_DISTANCE);
    mock.put(SatelliteNameEnum.kenobi, KENOBI_DISTANCE);
    return mock;
  }
  
  public SegmentedRequest getSegmentedRequest() {
    SegmentedRequest responseMock = new SegmentedRequest();
    responseMock.setDistance(SKYWALKER_DISTANCE);
    responseMock.setMessage(Arrays.asList(new String[]{"", "", "un", "", "", "prueba"}));
    return new SegmentedRequest();
  }
  
  public ResponseMessage buildMockResponse() {
    return ResponseMessage.builder().message(MESSAGE_TEST).build();
  }
  
}
