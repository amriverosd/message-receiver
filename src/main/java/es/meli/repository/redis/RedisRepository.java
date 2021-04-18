package es.meli.repository.redis;

import es.meli.controller.segmented.dto.SegmentedRequest;
import es.meli.model.SatelliteNameEnum;

public interface RedisRepository {

  void save(SatelliteNameEnum satellite, SegmentedRequest data);
  
  SegmentedRequest findByName(SatelliteNameEnum satellite);

  void delete(SatelliteNameEnum satelliteName);

}
