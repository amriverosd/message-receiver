package es.meli.repository.redis;

import es.meli.controller.segmented.dto.SegmentedRequest;
import es.meli.model.SatelliteNameEnum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisRepositoryImpl implements RedisRepository {

  private static final String MASTER_KEY = "Data";

  private final RedisTemplate<Object, Object> template;

  @Override
  public void save(SatelliteNameEnum satellite, SegmentedRequest data) {
    template.boundHashOps(MASTER_KEY).put(satellite, data);
    template.expire(MASTER_KEY, TimeUnit.DAYS.toMillis(1), TimeUnit.MILLISECONDS);
    template.setEnableTransactionSupport(false);
  }

  @Override
  public SegmentedRequest findByName(SatelliteNameEnum satellite) {
    return (SegmentedRequest) template.boundHashOps(MASTER_KEY).get(satellite);
  }

  @Override
  public void delete(SatelliteNameEnum satelliteName) {
    log.info("Delete {}", satelliteName);
    template.boundHashOps(MASTER_KEY).delete(satelliteName);
  }
}
