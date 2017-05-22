package com.icebrian.buses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextException;

/**
 * Entrance point for the SpringBoot application
 *
 */
@SpringBootApplication
public class BusesApplication {

	public static void main(String[] args) {
		
		//check that the file path argument is present
		if (args.length == 0 )  {
			throw new ApplicationContextException("Path to Route file not provided (--routeFilePath parameter is missing)");
		}

		SpringApplication.run(BusesApplication.class, args);

	}
}
