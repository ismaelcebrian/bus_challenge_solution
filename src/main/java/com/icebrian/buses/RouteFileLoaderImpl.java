package com.icebrian.buses;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RouteFileLoaderImpl implements RouteFileLoader {
	
	private Logger logger = Logger.getLogger(RouteFileLoaderImpl.class);
	
	@Value("${routeFilePath}")
	private String filePath;

	@Override
	public Map<Integer, List<Integer>> loadFile() throws FileNotFoundException, IOException {
		
		Map<Integer, List<Integer>> routesByStation = new HashMap<>();
		
		logger.info("Reading file with path: " + filePath);
		
	
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String firstLine = br.readLine();
			int numRoutes = Integer.parseInt(firstLine);
			logger.info(String.format("The file contains %d bus routes", numRoutes));
			String line;
			while ((line = br.readLine()) != null) {
	
				List<String> numbers = Arrays.asList(line.split(" "));
				Integer routeId = Integer.parseInt(numbers.get(0));
				for (int i = 1; i<numbers.size(); i++) {
					int stationId = Integer.parseInt(numbers.get(i));
					if (!routesByStation.containsKey(stationId)) {
						routesByStation.put(stationId, new ArrayList<>());
						
					}
					routesByStation.get(stationId).add(routeId);
				}
			}
			logger.info("File loaded");
		}
		return routesByStation;

	}

}
