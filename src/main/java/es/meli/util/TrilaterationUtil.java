package es.meli.util;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;

import es.meli.exception.UnprocessableMessage;
import es.meli.model.SatelliteNameEnum;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TrilaterationUtil {

  @Value("${config.kenobi}")
  private double[] kenobi;
  
  @Value("${config.skywalker}")
  private double[] skywalker;
  
  @Value("${config.sato}")
  private double[] sato;  

  /**
   * Gets the location based the distances and coordinates. 
   * @param satellitesInfo
   * @return position. 
   */
  public double[] getLocation(Map<SatelliteNameEnum, Double> satellitesInfo)  {
    double[] distances = new double[satellitesInfo.size()];
    double[][] positions = new double[satellitesInfo.size()][2];
    AtomicInteger count = new AtomicInteger(0);
    satellitesInfo.forEach(
        (key, value) -> addCoordinates(key, value, distances, positions, count.getAndIncrement()));
    
    log.info("Procesing the positions with coordenates: {}, distances {}", positions, distances);
    
    try {
      NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(
          new TrilaterationFunction(positions, distances), new LevenbergMarquardtOptimizer());
      Optimum optimum = solver.solve();
      return optimum.getPoint().toArray();
    } catch (IllegalArgumentException e) {
      log.error("Error: {}, {}", e.getMessage(), e.getCause());
      throw new UnprocessableMessage(
          String.format("Error getting the localization point: %s", e.getMessage()), e);
    }
  }

  
  private void addCoordinates(SatelliteNameEnum name, Double value, double[] distances,
      double[][] positions, int i) {
    distances[i] = value;
    positions[i] = getSatellitePosition(name);   
  }

  /**
   * Gets the point based on satellite name. 
   * @param name
   * @return position. 
   */
  private double[] getSatellitePosition(SatelliteNameEnum name) {
    switch (name) {
    case kenobi:
      return kenobi;
    case sato:
      return sato;
    case skywalker:
      return skywalker;
    }
    log.error("Error getting the satellite location for {}", name);
    throw new UnprocessableMessage(String.format("Satellite not fount: %s", name));
  }

}
