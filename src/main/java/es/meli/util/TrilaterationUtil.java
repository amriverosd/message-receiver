package es.meli.util;

import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;

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


  public double[] getLocation(double[] distances) {
    double[][] positions = new double[][] {kenobi, skywalker, sato};
    log.info("Procesing the positions with coordenates: {}, distances {}", positions, distances);
    NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(
        new TrilaterationFunction(positions, distances), new LevenbergMarquardtOptimizer());
    Optimum optimum = solver.solve();
    return optimum.getPoint().toArray();
  }

  
}
