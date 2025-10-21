package neqsim.process.util;

import java.util.Objects;

/**
 * New data structure to store device measurements.
 */

public final class DeviceMeasurement {
  private final String name;
  private double measuredValue;
  private final String unit;

  /**
   * Constructor.
   */
  public DeviceMeasurement(String name, double measuredValue, String unit) {
    this.name = name;
    this.measuredValue = measuredValue;
    this.unit = unit;
  }

  // Set measurement value
  public void setMeasuredValue(double measuredValue) {
    this.measuredValue = measuredValue;
  }

  // Getters
  public String getName() {
    return name;
  }

  public double getMeasuredValue() {
    return measuredValue;
  }

  public String getUnit() {
    return unit;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, measuredValue, unit);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    DeviceMeasurement other = (DeviceMeasurement) obj;
    return Double.compare(other.measuredValue, measuredValue) == 0
        && Objects.equals(name, other.name) && Objects.equals(unit, other.unit);

  }

}
