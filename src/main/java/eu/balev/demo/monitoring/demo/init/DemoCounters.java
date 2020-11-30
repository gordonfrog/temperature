//package eu.balev.demo.monitoring.demo.init;
//
//import io.micrometer.core.instrument.Counter;
//import io.micrometer.core.instrument.MeterRegistry;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.io.FileUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//
///**
// * Defines two sample counters belonging to the same
// * metrics named <pre>demoservice_heart_beat</pre>.
// *
// * The first counter is labelled with <pre>beat=beat1</pre>
// * and the second one is labelled with <pre>beat=beat2</pre>.
// * Both form two time series.
// */
//public class DemoCounters implements Runnable {
//
//  private static final Logger LOGGER = LoggerFactory.getLogger(DemoCounters.class);
//  //private final Counter beat1, beat2, temp;
//  private final Counter temp;
//  
//  public float reading;
//  
//  //@Value("${sensorsMasterDirectory:/sys/class/thermal/}")
//  private String SENSORS_MASTER_DIRECTORY = "/sys/class/thermal/";
//
//  DemoCounters(MeterRegistry meterRegistry) {
////    beat1 = Counter
////        .builder("demoservice_heart_beat")
////        .description("a simple counter")
////        .tags("beat", "beat1")
////        .register(meterRegistry);
////
////    beat2 = Counter
////        .builder("demoservice_heart_beat")
////        .description("a simple faster counter")
////        .tags("beat", "beat2")
////        .register(meterRegistry);
//    
//    temp = Counter
//            .builder("demoservice_heart_beat")
//            .description("a cpu temperature counter")
//            .tags("beat", "temp")
//            .register(meterRegistry);
//  }
//
//  @Override
//  public void run() {
//    while(true) {
//      try {
//        Thread.sleep(1000); //every sec
//        //beat1.increment(0.5);
//        //beat2.increment(1);
//        
//        float newReading = readSensor();
//        if (reading > 0 && newReading > 0) {
//        	//if (reading > newReading) {temp.increment(reading-newReading);}
//        	//else if (reading < newReading) {temp.increment(newReading-reading);}
//        	//if (reading != newReading) {float result = reading-newReading; LOGGER.info("Temperature Change: "+result); temp.increment(result);}
//        	if (reading != newReading) {float result = reading-newReading; LOGGER.info("Temperature Change: "+result);}
//        	else { LOGGER.info("No change in Temperature.");}
//        }
//        else { LOGGER.info("ERROR: Can't read Temperature."); }
//        reading = newReading;
//      } catch (InterruptedException e) {
//        Thread.currentThread().interrupt();
//        LOGGER.error("S.o. interrupted me and I'll go away :( Bye!", e);
//        return;
//      }
//    }
//  }
//  
//  private float readSensor() {
//	  try {
//		LOGGER.info("** Reading Sensor Files from: "+SENSORS_MASTER_DIRECTORY+" **");
//		for (final File file : new File(SENSORS_MASTER_DIRECTORY).listFiles()) {
//			if (!file.isDirectory()) {
//				continue;
//			}
//
//			final String filename = file.getName();
//			LOGGER.info("* filename: "+filename+" *");
//
//			if (!"thermal_zone0".equals(filename)) {
//				continue;
//			}
//
//			final String serialId = filename;
//			final float tempC = readTempC(SENSORS_MASTER_DIRECTORY + filename + "/temp");
//			LOGGER.info("tempC: "+tempC);
//			final float tempF = ((tempC * (9 / 5.0f)) + 32);
//			LOGGER.info("tempF: "+tempF);
//			
//			return tempF;
//		}
//	  } catch (Exception e) { LOGGER.info(e.getMessage()); }
//	  return 0;
//	}
//  
//  private float readTempC(final String location) throws Exception {
//		final String line = FileUtils.readLines(new File(location)).get(0);
//		//final String tempEqual = line.split(" ")[9];
//		//final int temp = Integer.parseInt(tempEqual.substring(2));
//		LOGGER.info("read: "+line);
//		final int temp = Integer.parseInt(line);
//		return temp / 1000f;
//	}
//}
