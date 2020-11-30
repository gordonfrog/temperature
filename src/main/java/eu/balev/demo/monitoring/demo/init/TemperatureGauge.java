package eu.balev.demo.monitoring.demo.init;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;

import java.io.File;
import java.util.function.ToDoubleFunction;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

class TemperatureGauge {
	
  private static final Logger LOGGER = LoggerFactory.getLogger(TemperatureGauge.class);
  
  //@Value("${sensorsMasterDirectory:/sys/class/thermal/}")
  public static String SENSORS_MASTER_DIRECTORY = "/sys/class/thermal/";

  TemperatureGauge(MeterRegistry meterRegistry) {
    //All of the different forms of creating a gauge maintain
    //only a weak reference to the object being observed,
    //so as not to prevent garbage collection of the object.

    Oscillator oscillator = new Oscillator();
    Gauge
        .builder("temperature_gauge", oscillator, oscillator)
        .description("a gauge to measure the cpu temperature")
        .register(meterRegistry);
  }

  private static class Oscillator implements ToDoubleFunction<Object> {
    
	private boolean increase = true;
    private double currentValue = 0;

    @Override
    public double applyAsDouble(Object value) {
      currentValue = currentValue + (increase ? 1 : -1);
      if (Math.abs(currentValue) > 0) {
        increase = !increase;
      }
      currentValue = readSensor();
      return currentValue;
    }
  }
  
  private static float readSensor() {
	  try {
		LOGGER.info("** Reading Sensor Files from: "+SENSORS_MASTER_DIRECTORY+" **");
		for (final File file : new File(SENSORS_MASTER_DIRECTORY).listFiles()) {
			if (!file.isDirectory()) {
				continue;
			}

			final String filename = file.getName();
			LOGGER.info("* filename: "+filename+" *");

			if (!"thermal_zone0".equals(filename)) {
				continue;
			}

			final String serialId = filename;
			final float tempC = readTempC(SENSORS_MASTER_DIRECTORY + filename + "/temp");
			LOGGER.info("tempC: "+tempC);
			final float tempF = ((tempC * (9 / 5.0f)) + 32);
			LOGGER.info("tempF: "+tempF);
			
			return tempF;
		}
	  } catch (Exception e) { LOGGER.info(e.getMessage()); }
	  return 0;
	}
  
  private static float readTempC(final String location) throws Exception {
		final String line = FileUtils.readLines(new File(location)).get(0);
		//final String tempEqual = line.split(" ")[9];
		//final int temp = Integer.parseInt(tempEqual.substring(2));
		LOGGER.info("read: "+line);
		final int temp = Integer.parseInt(line);
		return temp / 1000f;
	}
}