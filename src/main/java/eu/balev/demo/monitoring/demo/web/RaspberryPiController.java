package eu.balev.demo.monitoring.demo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.Gpio;

import eu.balev.demo.monitoring.demo.Status;

@RestController
public class RaspberryPiController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	public static GpioPinDigitalOutput pin;
	public static GpioPinDigitalOutput op;
		
//	@GetMapping("/raspberry/light")
//	public Status light(){
//		logger.info("/raspberry/light");
//		Status status = new Status();	
//		status.setCode("ON");
//		logger.info("returning status: "+status);
//		
//		return status;
//	}
//	@GetMapping("/raspberry/dark")
//	public Status dark(){
//		logger.info("/raspberry/dark");
//		Status status = new Status();	
//		status.setCode("OFF");
//		logger.info("returning status: "+status);
//		
//		return status;
//	}
	

	@GetMapping("/raspberry/light")
	public Status light(){
		logger.info("/raspberry/light");
		GpioController gpio = GpioFactory.getInstance();
		Status status = new Status();	
		if(pin==null){	        
	        pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_18, "MyLED", PinState.HIGH);
	        logger.info("pin: "+pin);
		}
		else logger.info("pin: "+pin);
		
		logger.info("is pin high? "+pin.isHigh());
		logger.info("is pin low? "+pin.isLow());
		if (pin.isLow()) {
			pin.high();
			pin.toggle();
		}
		status.setCode("ON");
		logger.info("returning status: "+status);
		return status;
	}
	@GetMapping("/raspberry/dark")
	public Status dark(){
		logger.info("/raspberry/dark");
		GpioController gpio = GpioFactory.getInstance();
		Status status = new Status();	
		if(pin==null){	        
	        pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_18, "MyLED", PinState.LOW);
	        logger.info("pin: "+pin);
		}
		else logger.info("pin: "+pin);
		logger.info("is pin high? "+pin.isHigh());
		logger.info("is pin low? "+pin.isLow());
		if(pin.isHigh()) {
			pin.low();
			pin.toggle();
		} 
		status.setCode("OFF");
		logger.info("returning status: "+status);
		return status;
	}
	
	@GetMapping("/raspberry/test1")
	public void test1() {
		logger.info("***************<--Pi4J--> GPIO Test1 ... started.***************");
        
        // create gpio controller
        GpioController gpio = GpioFactory.getInstance();
        
        // provision gpio pin #02 as an output pin and turn on
        if (pin==null) { logger.info("pin is null, setting to low"); pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_18, "MyLED-2", PinState.LOW); }
        else {
        	logger.info("STATE: "+pin.getState());
        	if (pin.getState().isHigh()) {logger.info("pin is high, setting to low"); pin.low();}
        	else {logger.info("pin is low, setting to high"); pin.high();}
        }
        // continuous loop
//        while(true)
//        {
//            pin.setState(true);
//            pin.setState(false);
//        }
	}
	
	@GetMapping("/raspberry/test2")
	public void test2() {
		logger.info("***************<--Pi4J--> GPIO Test2 ... started.****************");		
		if (Gpio.wiringPiSetup() == -1) {
			logger.info("GPIO SETUP ERROR");
            return;
        }
		else {
			logger.info("01: "+RaspiPin.GPIO_01);
			logger.info("02: "+RaspiPin.GPIO_02);
			logger.info("03: "+RaspiPin.GPIO_03);
			logger.info("04: "+RaspiPin.GPIO_04);
			logger.info("05: "+RaspiPin.GPIO_05);
			logger.info("06: "+RaspiPin.GPIO_06);
			logger.info("07: "+RaspiPin.GPIO_07);
			logger.info("08: "+RaspiPin.GPIO_08);
			logger.info("09: "+RaspiPin.GPIO_09);
			logger.info("10: "+RaspiPin.GPIO_10);
			logger.info("11: "+RaspiPin.GPIO_11);
			logger.info("12: "+RaspiPin.GPIO_12);
			logger.info("13: "+RaspiPin.GPIO_13);
			logger.info("14: "+RaspiPin.GPIO_14);
			logger.info("15: "+RaspiPin.GPIO_15);
			logger.info("16: "+RaspiPin.GPIO_16);
			logger.info("17: "+RaspiPin.GPIO_17);
			logger.info("18: "+RaspiPin.GPIO_18);
			logger.info("19: "+RaspiPin.GPIO_19);
			logger.info("20: "+RaspiPin.GPIO_20);
		}
//        
//        // create gpio controller
//        GpioController gpio = GpioFactory.getInstance();
//        
//        // provision gpio pin #02 as an output pin and turn on
//        if (pin==null) { logger.info("pin is null, setting to low"); pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "MyLED-2", PinState.LOW); }
//        else {
//        	logger.info("STATE: "+pin.getState());
//        	if (pin.getState().isHigh()) {logger.info("pin is high, setting to low"); pin.low();}
//        	else {logger.info("pin is low, setting to high"); pin.high();}
//        }
		
//		if (op == null) {
//			op = GpioFactory.getInstance().provisionDigitalOutputPin(RaspiPin.GPIO_18);
//			logger.info("GPIO_18 is Null"); return;
//		}
//		if (op.isHigh()) {logger.info("GPIO_18 is high, setting to low"); op.low(); return;}
//	    if (op.isLow()) {logger.info("GPIO_18 is low, setting to high"); op.high(); return;}
	    
	}
	
}
