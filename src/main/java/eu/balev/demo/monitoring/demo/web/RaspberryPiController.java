package eu.balev.demo.monitoring.demo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.balev.demo.monitoring.demo.Status;

@RestController
public class RaspberryPiController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
		
	@GetMapping("/raspberry/light")
	public Status light(){
		logger.info("/raspberry/light");
		Status status = new Status();	
		status.setCode("ON");
		logger.info("returning status: "+status);
		
		return status;
	}
	
	@GetMapping("/raspberry/dark")
	public Status dark(){
		logger.info("/raspberry/dark");
		Status status = new Status();	
		status.setCode("OFF");
		logger.info("returning status: "+status);
		
		return status;
	}
}
