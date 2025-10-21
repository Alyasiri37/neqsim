package neqsim.process.util;

import java.util.Objects;
import java.util.List;


/**
 * New data structure to store measurements history.
 */
public final class TimeStepData {
  private final double time;
  private final List<DeviceMeasurement> measurements;

  // Constructor
  public TimeStepData(double time, List<DeviceMeasurement> measurements) {
    this.time = time;
    this.measurements = measurements;
  }

  // Getters
  public double getTime() {
    return time;
  }

  public List<DeviceMeasurement> getMeasurements() {
    return measurements;
  }

  @Override
  public int hashCode() {
    return Objects.hash(time, measurements);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    TimeStepData other = (TimeStepData) obj;
    return Double.compare(other.time, time) == 0
        && Objects.equals(measurements, other.measurements);
  }
}
